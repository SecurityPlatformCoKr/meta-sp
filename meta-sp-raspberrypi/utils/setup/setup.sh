#!/bin/sh

if [ ! -d poky ]; then
git clone git://git.yoctoproject.org/poky.git -b yocto-2.0
fi

OLDWD=$PWD
cd poky
if [ ! -d meta-raspberrypi ]; then
git clone git://git.yoctoproject.org/meta-raspberrypi
fi

if [ ! -d meta-measured ]; then
git clone https://github.com/flihp/meta-measured.git
cd meta-measured
git checkout a5e41f44bbccd9c0a9a4018937d1c08743b18f3c
cd -
fi
if [ ! -d iot-black ]; then
git clone https://github.com/SecurityPlatformCoKr/meta-sp iot-black
fi

patch -p1 < iot-black/meta-sp-raspberrypi/utils/setup/poky.meta-yocto.conf.bblayers.conf.sample.patch
if [ $? != 0 ]; then echo "[FAIL]: patch"; exit 0; fi

cd $OLDWD

mkdir -p build/conf
if [ ! -e build/conf/local.conf ]; then
cat << EOF > build/conf/local.conf
MACHINE ??= "raspberrypi2"
GPU_MEM = "16"
PACKAGE_CLASSES ?= "package_ipk"
DISTRO ?= "poky-raspberrypi2"
EXTRA_IMAGE_FEATURES = "debug-tweaks"
USER_CLASSES ?= "buildstats image-mklibs image-prelink"
PATCHRESOLVE = "noop"
CONF_VERSION = "1"
KERNEL_IMAGETYPE="uImage"
KERNEL_DEVICETREE += "bcm2709-rpi-2-b-tpm-infineon.dtb bcm2709-rpi-2-b-tpm-atmel.dtb"
SDIMG_ROOTFS_TYPE = "ext4"
IMAGE_ROOTFS_SIZE = "524288"
EOF

fi

echo 'run "source poky/oe-init-build-env"; bitbake sp-rpi-image'
