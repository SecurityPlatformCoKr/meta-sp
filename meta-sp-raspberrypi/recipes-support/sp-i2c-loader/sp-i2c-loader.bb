DESCRIPTION = "tpm driver initializer for raspberrypi2"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://i2c-bcm2708.conf \
	   file://i2c-dev.conf \
	   file://tpm-i2c-atmel.conf \
	  "

do_install() {
    install -d ${D}${sysconfdir}/modules-load.d/
    install -m 0600 ${WORKDIR}/i2c-bcm2708.conf ${D}${sysconfdir}/modules-load.d/
    install -m 0600 ${WORKDIR}/i2c-dev.conf ${D}${sysconfdir}/modules-load.d/
    install -m 0600 ${WORKDIR}/tpm-i2c-atmel.conf ${D}${sysconfdir}/modules-load.d/
}

FILES_${PN} += "${sysconfdir}/modules-load.d/*"
