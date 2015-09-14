require recipes-bsp/u-boot/u-boot.inc
require recipes-bsp/u-boot/u-boot-target-env.inc
require recipes-bsp/u-boot/u-boot-osip.inc

require u-boot-internal.inc

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=025bf9f768cbcb1a165dbe1a110babfb"

SRC_URI += " file://dt-pubkey.dtb"
SRC_URI += " file://fit_image_x86_setup.patch"

do_compile_append() {
    if [ -e ${WORKDIR}/dt-pubkey.dtb ]; then
	make EXT_DTB=${WORKDIR}/dt-pubkey.dtb
    fi
}
