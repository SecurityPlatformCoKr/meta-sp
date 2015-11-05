SUMMARY = "boot configuration for u-boot extlinux"
COMPATIBLE_MACHINES = "raspberrypi2"
SECTION = "kernel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://extlinux.conf"

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}/bcm2835-bootfiles/extlinux
    install -m 0600 ${WORKDIR}/extlinux.conf ${DEPLOYDIR}/bcm2835-bootfiles/extlinux/
}

DEPENDS = "rpi-config"

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bcm2835-bootfiles/extlinux"

PACKAGE_ARCH = "${MACHINE_ARCH}"
