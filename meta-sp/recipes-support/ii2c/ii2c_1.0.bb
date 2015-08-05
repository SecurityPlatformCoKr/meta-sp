DESCRIPTION = "i2c initializer on edison/galileo"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS="mraa"

SRC_URI = "file://ii2c.c \
	   file://Makefile \
	   file://ii2c.service \
	  "

S = "${WORKDIR}"
do_compile() {
    make ii2c
}

do_install() {
    make DESTDIR=${D} install
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/ii2c.service ${D}${systemd_unitdir}/system/
}

inherit systemd

SYSTEMD_SERVICE_${PN} = "ii2c.service"
