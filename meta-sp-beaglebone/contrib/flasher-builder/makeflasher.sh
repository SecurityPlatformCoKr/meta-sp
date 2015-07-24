#!/bin/bash

CONFFILE="makeflasher.conf" 
if [ "$1" != "" ]; then
    CONFFILE=$1
fi
if [ ! -e ${CONFFILE} ]; then
    echo "no configuration. exit"
    exit
fi

. ${CONFFILE}

if [ ! -z "${TARGETDEV}" ]; then
    if [ "${TARGETDEV}" == "detect-microsd" ]; then
	TARGETDEV=`dmesg | tail -20 | grep "Attached SCSI removable disk" | sed -e "s/[]\[]/ /g" | awk -F ' ' '{print $4}'`
	if [ "$TARGETDEV" == "" ]; then
	    echo "DEVICE NOT DETECTED. plug it again"
	    exit 0
	fi
    fi
else
    if [ "${IMAGESIZE_MB}" == "" ]; then
	echo "IMAGESIZE_MB empty. Aborting..."
	exit 1
    fi
fi
echo "USING ${TARGETIMAGE}..."

ID=`id -u`
if [ ${ID} != "0" ]; then
    echo "id not root. Please run with 'sudo'. Aborting..."
    exit 1
fi

IMG=tmp_flasher.img

make_zeroimage() {
    if [ ! -z "${TARGETDEV}" ]; then
	return
    fi
    dd if=/dev/zero of=${IMG} bs=1M count=${IMAGESIZE_MB}
    if [ "$?" != "0" ]; then
	echo "dd failed. aborting..."
	exit 1
    fi
    chown ${SUDO_USER} ${CONFFILE}
}

LOOPDEV="/dev/loop0"
LOOP0="loop0"
LOOP0P1="mapper/loop0p1"
LOOP0P2="mapper/loop0p2"

losetup_add_img() {
    losetup -f ${IMG}
}

losetup_detach_img() {
    losetup -d ${LOOPDEV}
}

fdisk_img() {
    if [ -z "${TARGETDEV}" ]; then
	losetup_add_img
	LOOPDEV=`losetup -a | grep tmp_flasher.img | head -1 | awk -F: '{print $1}'`
	echo "losetup -a "${LOOPDEV}
    else
	LOOPDEV="/dev/${TARGETDEV}"
	LOOP0="${TARGETDEV}"
	LOOP0P1="${TARGETDEV}1"
	LOOP0P2="${TARGETDEV}2"
	umount /dev/${LOOP0P1}
	umount /dev/${LOOP0P2}
    fi

    #dd if=${IMAGEDIR}/MLO of=${LOOPDEV} count=1 seek=1 conv=notrunc bs=128k
    #dd if=${IMAGEDIR}/u-boot.img of=${LOOPDEV} count=2 seek=1 conv=notrunc bs=384k
#    sfdisk -H 255 -S 63 -C 15 ${LOOPDEV} << EOF
    sfdisk ${LOOPDEV} << EOF
,4,C,*
,
EOF
    if [ -z "${TARGETDEV}" ]; then
	losetup_detach_img
    fi
}

gen_mappeddev() {
    if [ ! -z "${TARGETDEV}" ]; then
	return 
    fi
    LOOP0ALL=`kpartx -av ${IMG} | awk -F' ' '{print $3}'`
    if [ "$?" != "0" ]; then echo "KPARTX ${IMG} FAILED. Aborting..."; exit 1; fi
    LOOP0=`echo ${LOOP0ALL} | awk -F' ' '{print $1}' | sed -e "sp1$//"`
}

del_mappeddev() {
    if [ ! -z "${TARGETDEV}" ]; then
	return 
    fi
    kpartx -d ${IMG}
}

abort() {
    echo "Aborting..."
    del_mappeddev

    echo "System Status:"
    losetup -a
    exit 1
}

mkfs_img() {
    echo "mkfs LOOP0 " $LOOP0
    echo "Making filesystem..."
    mkfs -t vfat -n BEAGLE_BONE /dev/${LOOP0P1} 
    if [ "$?" != "0" ]; then echo "MKFS VFAT FAILED."; abort; fi
    mkfs -t ext4 -L eMMC-Flasher /dev/${LOOP0P2}
    if [ "$?" != "0" ]; then echo "MKFS EXT4 FAILED."; abort; fi
}

MOUNTPOINT1=1
MOUNTPOINT2=2

mount_img() {
    mkdir -p ${MOUNTPOINT1}
    mount /dev/${LOOP0P1} ${MOUNTPOINT1}
    if [ "$?" != "0" ]; then echo "MOUNT p1 FAILED."; abort; fi

    mkdir -p ${MOUNTPOINT2}
    mount /dev/${LOOP0P2} ${MOUNTPOINT2}
    if [ "$?" != "0" ]; then echo "MOUNT p2 FAILED."; abort; fi
    echo "COMPLETE: mount"
}

umount_img() {
    umount ${MOUNTPOINT1}
    if [ "$?" != "0" ]; then echo "UMOUNT p1 FAILED."; fi
    rmdir ${MOUNTPOINT1}

    umount ${MOUNTPOINT2}
    if [ "$?" != "0" ]; then echo "UMOUNT p2 FAILED."; fi
    rmdir ${MOUNTPOINT2}

    echo "COMPLETE: umount"
}

fill_p1() {
    UUID=`sudo blkid /dev/${LOOP0P2} | awk -F '"' '{print $4}'`
    cp ${IMAGEDIR}/MLO ${MOUNTPOINT1}/
    cp ${IMAGEDIR}/u-boot-dtb.img ${MOUNTPOINT1}/u-boot.img
    echo "Security Platform for ${TARGETIMAGE}" > ${MOUNTPOINT1}/ID.txt
    echo "optargs=quiet" > ${MOUNTPOINT1}/uEnv.txt
    #echo "mmcroot=UUID=${UUID} rw rootdelay=1" > ${MOUNTPOINT1}/uEnv.txt
    #echo "setenv loadzimage ext4load mmc 0:2 0x80300000 /boot/zImage" >> ${MOUNTPOINT1}/uEnv.txt
    #echo "setenv loaddtb ext4load mmc 0:2 0x815f0000 /boot/am335x-boneblack.dtb" >> ${MOUNTPOINT1}/uEnv.txt
    #echo "setenv bootcmd run loadzimage; run loaddtb; bootz 0x80300000 - 0x815f0000" >> ${MOUNTPOINT1}/uEnv.txt
    echo "console=ttyO0,115200n8" >> ${MOUNTPOINT1}/uEnv.txt
    echo "setenv console ttyO0,115200n8" >> ${MOUNTPOINT1}/uEnv.txt

}

IMAGEBASE=`basename ${TARGETIMAGE} .tar.xz`
TODAY=`date -I`
fill_p2() {
    tar Jxf ${IMAGEDIR}/${FLASHERIMG} -C ${MOUNTPOINT2}/
    mknod ${MOUNTPOINT2}/dev/mmcblk1p1 b 179 9
    mknod ${MOUNTPOINT2}/dev/mmcblk1p2 b 179 10

    mkdir -p ${MOUNTPOINT2}/boot/uboot
    echo "/dev/mmcblk0p1  /boot/uboot	vfat	sync	0 0" >> ${MOUNTPOINT2}/etc/fstab

    mkdir -p ${MOUNTPOINT2}/etc/systemd/system/basic.target.wants

    cp materials/emmc.service ${MOUNTPOINT2}/lib/systemd/system/
    ln -s /lib/systemd/system/emmc.service ${MOUNTPOINT2}/etc/systemd/system/basic.target.wants


    /bin/date -u +%2m%2d%2H%2M%4Y > ${MOUNTPOINT2}/etc/timestamp
    cp materials/timestamp.service ${MOUNTPOINT2}/lib/systemd/system/
    ln -s /lib/systemd/system/timestamp.service ${MOUNTPOINT2}/etc/systemd/system/basic.target.wants

    cp materials/load-timestamp.sh ${MOUNTPOINT2}/usr/bin
    echo "SP ${IMAGEBASE} ${TODAY}" > ${MOUNTPOINT2}/etc/releases
    if [ "${RESCUE}" != "yes" ]; then
	cp materials/emmc.sh ${MOUNTPOINT2}/usr/bin

	mkdir ${MOUNTPOINT2}/build
	cp materials/mkcard.sh ${MOUNTPOINT2}/build
	cp ${IMAGEDIR}/${TARGETIMAGE} ${MOUNTPOINT2}/build
	sed -i -e "s/Jxf Angstrom-Cloud9-IDE-GNOME-eglibc-ipk-v2013.06-beaglebone.rootfs.tar.xz/Jxf ${TARGETIMAGE}/" ${MOUNTPOINT2}/usr/bin/emmc.sh

	MLOMD5="$(md5sum ${MOUNTPOINT1}/MLO | awk '{print $1}')"
	UBOOTMD5="$(md5sum ${MOUNTPOINT1}/u-boot.img | awk '{print $1}')"
	sed -i -e s:DATE:${DATE}:g \
	       -e s:MD5MLO:${MLOMD5}:g \
	       -e s:MD5UBOOT:${UBOOTMD5}:g \
	       ${MOUNTPOINT2}/usr/bin/emmc.sh

	cp ${IMAGEDIR}/MLO ${MOUNTPOINT2}/build
	cp ${IMAGEDIR}/u-boot-dtb.img ${MOUNTPOINT2}/build/u-boot.img
	if [ -e ${IMAGEDIR}/image.fit ]; then
	    cp ${IMAGEDIR}/image.fit ${MOUNTPOINT2}/build/
	fi
    fi

}

archive_img() {
    if [ ! -z "${TARGETDEV}" ]; then
	return 
    fi
    if [ "${RESCUE}" == "yes" ]; then
	mv ${IMG} rescue-${IMAGEBASE}-${TODAY}.img
    else
	mv ${IMG} ${IMAGEBASE}-eMMC-Flasher.`date -I`.img
    fi
}

make_zeroimage 
fdisk_img
gen_mappeddev
mkfs_img
mount_img
fill_p1
fill_p2
umount_img
del_mappeddev
archive_img
