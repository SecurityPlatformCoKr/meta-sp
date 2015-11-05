#!/bin/bash

top_repo_dir=$(dirname $(dirname $(dirname $(dirname $(dirname $(dirname $0))))))
pushd ${top_repo_dir} > /dev/null
top_repo_dir=`pwd`
popd > /dev/null
pushd $(dirname $0) > /dev/null
sign_dir=`pwd`
popd > /dev/null

build_dir=${top_repo_dir}/build

#
# Prepare stubs for signing
#

uboot_dir=${build_dir}/tmp/work/raspberrypi2-poky-linux-gnueabi/u-boot
dt_dtb=`find ${uboot_dir} -name dt.dtb`
if [ x${dt_dtb} == "x" ]; then
    echo "no dtb file. check u-boot version or build u-boot first"
    exit 1
fi

linuxyocto_dir=${build_dir}/tmp/work/raspberrypi2-poky-linux-gnueabi/linux-raspberrypi
Image=`find ${linuxyocto_dir} -type f -name Image | head -1`
if [ x${Image} == "x" ]; then
    echo "no Image file. build linux-yocto first"
    exit 1
fi

sign_its="${sign_dir}/sign-rpi2.its"
if [ ! -e ${sign_its} ]; then
    echo "no its file"
    exit 1
fi

mkdir -p signKernel
cp -f ${dt_dtb} signKernel/
if [ $? != 0 ]; then echo fail: copy ${dt_dtb}; exit 1; fi
cp -f ${Image} signKernel/Image
if [ $? != 0 ]; then echo fail: objcopy ${Image}; exit 1; fi
lzop -f signKernel/Image
if [ $? != 0 ]; then echo fail: lzop ${Image}; exit 1; fi
echo cp -f ${sign_its} signKernel/
cp -f ${sign_its} signKernel/sign.its
if [ $? != 0 ]; then echo fail: copy ${sign_its}; exit 1; fi

mkimage=`find ${uboot_dir} -name mkimage | head -1`
if [ ! -e ${mkimage} ]; then
    echo "no mkimage, build u-boot first"
    exit 1
fi

#
# Sign kernel-image
#
cd signKernel
keys_dir=${top_repo_dir}/poky/meta-sp/keys

cp dt.dtb dt-pubkey.dtb
${mkimage} -f sign.its -K dt-pubkey.dtb -k ${keys_dir} -r image.fit

#
# add image.fit to sp-rpi-image-raspberrypi2.rpi-sdimg

rpisdimg=${build_dir}/tmp/deploy/images/raspberrypi2/sp-rpi-image-raspberrypi2.rpi-sdimg
if [ -e ${rpisdimg} ]; then
    ls ${rpisdimg}
#    if mdir -i ${rpisdimg} ::/ | grep -q vmlinuz; then
#	mdel -i ${rpisdimg} ::/vmlinuz
#    fi
#    if mdir -i ${rpisdimg} ::/ | grep -q "image    fit"; then
#	mdel -i ${rpisdimg} ::/image.fit
#    fi
#    mcopy -i ${rpisdimg} -n image.fit ::/
fi
