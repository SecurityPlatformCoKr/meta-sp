FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd
SYSTEMD_SERVICE_${PN} = "trousers.service"

SRC_URI += "file://trousers.service \
	    "

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/trousers.service ${D}${systemd_unitdir}/system
}
