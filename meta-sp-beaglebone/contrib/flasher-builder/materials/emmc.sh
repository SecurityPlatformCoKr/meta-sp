#!/bin/bash

# (c) Copyright 2013 Koen Kooi <koen@dominion.thruhere.net>
# Licensed under terms of GPLv2

FORCE=$1
if [ "${FORCE}" != "--force" ]; then
    if grep -q rescue /proc/cmdline; then
	echo "Boot in RESCUE mode"
	exit 0
    fi
fi

export PATH=$PATH:/bin:/sbin:/usr/bin:/usr/sbin

PART1MOUNT="/media/1"
PART2MOUNT="/media/2"

HOSTARCH="$(uname -m)"

MLOMD5="MD5MLO"
UBOOTMD5="MD5UBOOT"

cd /build

HEADER=$(hexdump -e '8/1 "%c"' /sys/bus/i2c/devices/0-0050/eeprom -s 5 -n 3)

if [ ${HEADER} -eq 335 ] ; then
        echo "Valid EEPROM header found"
else
        echo "Invalid EEPROM header detected"
	if [ -e /eeprom.dump ] ; then
		echo "Adding header to EEPROM"
		dd if=/eeprom.dump of=/sys/devices/ocp.2/44e0b000.i2c/i2c-0/0-0050/eeprom
	fi
fi

if ! [ -e /dev/mmcblk1 ] ; then
	echo "eMMC device not found!"
	exit
fi

echo "Paritioning eMMC"
dd if=/dev/zero of=/dev/mmcblk1 bs=16M count=16
./mkcard.sh /dev/mmcblk1

mknod /dev/mmcblk1p1 b 179 9
mknod /dev/mmcblk1p2 b 179 10

echo "Mounting partitions"
mkdir -p ${PART1MOUNT}
mkdir -p ${PART2MOUNT}
mount /dev/mmcblk1p1 ${PART1MOUNT} -o sync
mount /dev/mmcblk1p2 ${PART2MOUNT} -o async,noatime

echo "Copying bootloader files"
cp MLO u-boot.img ${PART1MOUNT}
echo "optargs=quiet ima=on ima_tcb ima_appraise_tcb ima_appraise=fix rootflags=i_version" >> ${PART1MOUNT}/uEnv.txt
echo "console=ttyO0,115200n8" >> ${PART1MOUNT}/uEnv.txt
echo "setenv console ttyO0,115200n8" >> ${PART1MOUNT}/uEnv.txt
echo "bootfile=image.fit" >> ${PART1MOUNT}/uEnv.txt
echo "boot_fdt=no" >> ${PART1MOUNT}/uEnv.txt

umount /dev/mmcblk1p1

sync

echo "Extracting rootfs"
tar Jxf Angstrom-Cloud9-IDE-GNOME-eglibc-ipk-v2013.06-beaglebone.rootfs.tar.xz -C ${PART2MOUNT} --numeric-owner

echo "Populating VFAT partition"
mount /dev/mmcblk1p1 ${PART1MOUNT} -o async
if [ -d ${PART2MOUNT}/usr/share/beaglebone-getting-started ] ; then
	cp -r ${PART2MOUNT}/usr/share/beaglebone-getting-started/* ${PART1MOUNT}
fi

cp /boot/uboot/ID.txt ${PART1MOUNT}/ID.txt
cp /boot/uboot/ID.txt ${PART2MOUNT}/etc/dogtag
if [ -e image.fit ]; then
    cp image.fit ${PART2MOUNT}/boot
fi

umount /dev/mmcblk1p1

if [ "${HOSTARCH}" = "armv7l" ] ; then

	echo "Generating machine ID"
	systemd-nspawn -D ${PART2MOUNT} /bin/systemd-machine-id-setup

	echo "Running Postinsts"
	cpufreq-set -g performance
	systemd-nspawn -D ${PART2MOUNT} /usr/bin/opkg-cl configure
	if [ -e ${PART2MOUNT}/etc/rcS.d/S98run-postinsts ] ; then 
		systemd-nspawn -D ${PART2MOUNT} /etc/rcS.d/S98run-postinsts
		rm -f ${PART2MOUNT}/etc/rcS.d/S98run-postinsts
	fi
	cpufreq-set -g ondemand

	# Hack to get some space back
	systemd-nspawn -D ${PART2MOUNT} /usr/bin/opkg-cl remove db-doc --force-depends

	#echo "Setting timezone to Europe/Paris"
	#systemd-nspawn -D ${PART2MOUNT} /usr/bin/timedatectl set-timezone Europe/Paris

fi

rm -f ${PART2MOUNT}/etc/pam.d/gdm-autologin

rm -f ${PART2MOUNT}/etc/systemd/system/multi-user.target.wants/xinput-calibrator.service
rm -f ${PART2MOUNT}/etc/systemd/system/multi-user.target.wants/busybox*
rm -f ${PART2MOUNT}/etc/dropbear/dropbear_rsa_host_key
ln -s /dev/null ${PART2MOUNT}/etc/systemd/system/xinetd.service

touch ${PART2MOUNT}/etc/default/locale

# enable wifi
mkdir -p ${PART2MOUNT}/var/lib/connman/
cp connman.settings ${PART2MOUNT}/var/lib/connman/settings

rm -f ${PART2MOUNT}/etc/network/interfaces

# Replace wallpaper
if [ -e ${PART2MOUNT}/usr/share/pixmaps/backgrounds/gnome/angstrom-default.jpg ] ; then
	cp beaglebg.jpg ${PART2MOUNT}/usr/share/pixmaps/backgrounds/gnome/angstrom-default.jpg
fi

if [ -e ${PART2MOUNT}/usr/bin/init-tpm.sh ]; then
    touch ${PART2MOUNT}/S0
    ln -sf runlevel1.target ${PART2MOUNT}/lib/systemd/system/default.target
    ln -sf init-tpm.sh ${PART2MOUNT}/usr/bin/plymouth
fi

sync
umount ${PART2MOUNT}

# verification stage

mount /dev/mmcblk1p1 ${PART1MOUNT}

if [ -e ${PART1MOUNT}/ID.txt ] ; then
	echo "ID.txt found"
else
	echo "ID.txt missing - ERROR"
	ERROR="ID.txt"
fi

#if [ -e ${PART1MOUNT}/START.htm ] ; then
#	echo "START.htm found"
#else
#	echo "START.htm missing - ERROR"
#	ERROR="${ERROR}, START.htm"
#fi

if [ "${MLOMD5}" != "$(md5sum ${PART1MOUNT}/MLO | awk '{print $1}')" ] ; then        
	echo "MLO MD5sum failed"       
	ERROR="${ERROR}, MLO"  
fi   
 
if [ "${UBOOTMD5}" != "$(md5sum ${PART1MOUNT}/u-boot.img | awk '{print $1}')" ] ; then
	echo "u-boot MD5sum failed"
	ERROR="${ERROR}, uboot"
fi

umount /dev/mmcblk1p1

mount /dev/mmcblk1p2 ${PART2MOUNT} -o async,noatime

umount ${PART2MOUNT}

# force writeback of eMMC buffers
dd if=/dev/mmcblk1 of=/dev/null count=100000

if [ -z "$ERROR" ] ; then
	if [ -e /sys/class/leds/beaglebone\:green\:usr0/trigger ] ; then
		echo default-on > /sys/class/leds/beaglebone\:green\:usr0/trigger
		echo default-on > /sys/class/leds/beaglebone\:green\:usr1/trigger
		echo default-on > /sys/class/leds/beaglebone\:green\:usr2/trigger
		echo default-on > /sys/class/leds/beaglebone\:green\:usr3/trigger
	fi
else
	echo "ERRORS found: ${ERROR}"
	if [ -e /sys/class/leds/beaglebone\:green\:usr0/trigger ] ; then
		echo none > /sys/class/leds/beaglebone\:green\:usr0/trigger
		echo none > /sys/class/leds/beaglebone\:green\:usr1/trigger
		echo none > /sys/class/leds/beaglebone\:green\:usr2/trigger
		echo none > /sys/class/leds/beaglebone\:green\:usr3/trigger
	fi
fi
