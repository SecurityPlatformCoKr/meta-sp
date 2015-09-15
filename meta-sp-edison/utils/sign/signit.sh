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

uboot_dir=${build_dir}/tmp/work/edison-poky-linux/u-boot
dt_dtb=`find ${uboot_dir} -name dt.dtb`
if [ x${dt_dtb} == "x" ]; then
    echo "no dtb file. check u-boot version or build u-boot first"
    exit 1
fi

linuxyocto_dir=${build_dir}/tmp/work/edison-poky-linux/linux-yocto
vmlinux=`find ${linuxyocto_dir} -type f -name vmlinux | head -1`
if [ x${vmlinux} == "x" ]; then
    echo "no vmlinux.bin file. build linux-yocto first"
    exit 1
fi

setupbin=`find ${linuxyocto_dir} -name setup.bin | head -1`
if [ x${setupbin} == "x" ]; then
    echo "no setup.bin file. build linux-yocto first"
    exit 1
fi

sign_its="${sign_dir}/sign.its"
if [ ! -e ${sign_its} ]; then
    echo "no its file"
    exit 1
fi

mkdir -p signKernel
cp -f ${dt_dtb} signKernel/
if [ $? != 0 ]; then echo fail: copy ${dt_dtb}; exit 1; fi
objcopy -O binary ${vmlinux} signKernel/vmlinux.bin
if [ $? != 0 ]; then echo fail: objcopy ${vmlinux}; exit 1; fi
cp ${setupbin} signKernel/setup.bin
if [ $? != 0 ]; then echo fail: cp ${setupbin}; exit 1; fi
lzma -f --stdout signKernel/vmlinux.bin > signKernel/vmlinux.lzma 
if [ $? != 0 ]; then echo fail: lzma ${vmlinux}; exit 1; fi
echo cp -f ${sign_its} signKernel/
cp -f ${sign_its} signKernel/
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
keys_dir=${top_repo_dir}/sp/meta-sp/keys

cp dt.dtb dt-pubkey.dtb
${mkimage} -f sign.its -K dt-pubkey.dtb -k ${keys_dir} -r image.fit

#
# add image.fit to edison-image-edison.hddimg

edisonimage=${build_dir}/tmp/deploy/images/edison/edison-image-edison.hddimg
if [ -e ${edisonimage} ]; then
    if mdir -i ${edisonimage} ::/ | grep -q vmlinuz; then
	mdel -i ${edisonimage} ::/vmlinuz
    fi
    if mdir -i ${edisonimage} ::/ | grep -q "image    fit"; then
	mdel -i ${edisonimage} ::/image.fit
    fi
    mcopy -i ${edisonimage} -n image.fit ::/
fi
