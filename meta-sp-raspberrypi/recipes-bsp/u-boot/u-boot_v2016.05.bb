DESCRIPTION = "U-Boot for RaspberryPi2"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "rpi-mkimage-native"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PUBKEYDTB="bcm2709-rpi-2-b-pubkey.dtb"
SRC_URI = "git://github.com/SecurityPlatformCoKr/u-boot.git;branch=u-boot-2016.05;protocol=https;rev=812f999030441fe5c8db25dcd824d42c0749e38c \
	    file://${PUBKEYDTB} \
	    "
SRC_URI[md5sum] = "2fe3752f96f295fe545a268b17194e5a"

S = "${WORKDIR}/git"

OVERRIDES="${MACHINE}"
UBOOT_MACHINE_raspberrypi2 = "rpi_2_defconfig"
UBOOT_MACHINE_raspberrypi3 = "rpi_3_32b_defconfig"
UBOOT_MAKE_TARGET = "u-boot-dtb.bin EXT_DTB=${WORKDIR}/${PUBKEYDTB}"
UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot.img"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "raspberrypi2"

do_compile_append() {
    cp -f u-boot-dtb.bin u-boot.bin
    cp -f u-boot-dtb.bin u-boot.img
    ## Create kernel.img from uboot.bin and name it u-boot.img
    #${STAGING_DIR_NATIVE}/usr/lib/rpi-mkimage/imagetool-uncompressed.py u-boot.bin
    #mv kernel.img u-boot.img
}
