DESCRIPTION = "i2c initializer on edison/galileo"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS="mraa"

SRC_URI = "file://ii2c.c \
	   file://Makefile \
	  "
S = "${WORKDIR}"
do_compile() {
    make ii2c
}

do_install() {
    make DESTDIR=${D} install
}

