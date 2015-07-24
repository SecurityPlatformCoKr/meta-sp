#!/bin/bash

PROOT=/home/kkim/B
BB_TOP=${PROOT}/build/tmp-angstrom_v2014_06-eglibc
BB_WORK=${BB_TOP}/work/beaglebone-angstrom-linux-gnueabi/
BB_DEPLOY=${PROOT}/deploy/eglibc/images/beaglebone
UBOOT=${BB_WORK}/u-boot-denx/2014.07_vboot-vboot/git/

TARFILE=${BB_DEPLOY}/Angstrom-sp-image-eglibc-ipk-v2014.06-beaglebone.rootfs.tar
XZFILE=${TARFILE}.xz 

tar Jxf ${XZFILE} ./boot/am335x-boneblack.dtb && mv boot/am335x-boneblack.dtb . 


ln -sf ${BB_DEPLOY}/Image || exit 1

rm -f Image.lzo
lzop Image || exit 1
echo "Generating Image.lzo..."

${BB_TOP}/sysroots/x86_64-linux/usr/bin/mkimage -f sign.its -k keys -r image.fit || exit 1
echo "Check image.fit"

cp image.fit ${BB_DEPLOY}/
