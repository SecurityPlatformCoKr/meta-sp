DESCRIPTION = "tpm-i2c-atmel on Galileo II"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://en_i2c_tpm.service \
	   file://tpm-i2c-atmel.conf \
	  "

S = "${WORKDIR}"
do_install() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/en_i2c_tpm.service ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${WORKDIR}/tpm-i2c-atmel.conf ${D}${sysconfdir}/modules-load.d/
}

inherit systemd

RDEPENDS_${PN} += "ii2c"

SYSTEMD_SERVICE_${PN} = "en_i2c_tpm.service"
