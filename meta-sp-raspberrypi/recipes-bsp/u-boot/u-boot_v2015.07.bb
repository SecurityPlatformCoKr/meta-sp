DESCRIPTION = "U-Boot for RaspberryPi2"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "rpi-mkimage-native"

PROVIDES += "u-boot"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://git.denx.de/u-boot.git;tag=v2015.07"

S = "${WORKDIR}/git"

UBOOT_MACHINE = "rpi_2_defconfig"
UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "img"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "raspberrypi2"

do_compile_append() {
    # Create kernel.img from uboot.bin and name it u-boot.img
    ${STAGING_DIR_NATIVE}/usr/lib/rpi-mkimage/imagetool-uncompressed.py u-boot.bin
    mv kernel.img u-boot.img
}
