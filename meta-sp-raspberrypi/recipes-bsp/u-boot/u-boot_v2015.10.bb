DESCRIPTION = "U-Boot for RaspberryPi2"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "rpi-mkimage-native"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

PUBKEYDTB="bcm2709-rpi-2-b-pubkey.dtb"
SRC_URI = "git://git.denx.de/u-boot.git;tag=v2015.10 \
	    file://add_dts_and_fit_rsa_option_to_rpi2.patch \
	    file://${PUBKEYDTB} \
	    "

S = "${WORKDIR}/git"

UBOOT_MACHINE = "rpi_2_defconfig"
UBOOT_MAKE_TARGET = "u-boot-dtb.bin EXT_DTB=${WORKDIR}/dt-pubkey.dtb"
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
