FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://openvpn@.service \
	    file://server.conf \
	    file://client.conf \
	    "

do_install_append() {
    install -d ${D}/var/lib/openvpn
    install -m 0600 ${WORKDIR}/server.conf ${D}${sysconfdir}/openvpn
    install -m 0600 ${WORKDIR}/client.conf ${D}${sysconfdir}/openvpn
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/openvpn@.service ${D}${systemd_unitdir}/system/
}

EXTRA_OECONF += "--enable-systemd"

inherit systemd

SYSTEMD_SERVICE_${PN} = "openvpn@.service"
SYSTEMD_AUTO_ENABLE ??= "disable"

PACKAGES =+ "${PN}-server ${PN}-client"

FILES_${PN}-server = "${sysconfdir}/openvpn/server.conf"
FILES_${PN}-client = "${sysconfdir}/openvpn/client.conf"

RDEPENDS_${PN}-server += "${PN}"
RDEPENDS_${PN}-client += "${PN}"
