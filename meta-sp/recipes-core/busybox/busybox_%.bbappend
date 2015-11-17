# to get brctl and log configuration settings
FILESEXTRAPATHS_prepend := "${THISDIR}/files/:"

PACKAGE =+ "${PN}-udhcpd"

SRC_URI += "file://udhcpd.conf \
	    file://busybox-udhcpd.service \
	   "
SRC_URI += " file://syslogd.cfg "

inherit systemd
SYSTEMD_SERVICE_${PN}-udhcpd = "busybox-udhcpd.service"

FILES_${PN}-udhcpd = "${sysconfdir}/udhcpd.conf \
		      ${systemd_unitdir}/system/busybox-udhcpd.service \
                     "
do_install_append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/udhcpd.conf ${D}${sysconfdir}/
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/busybox-udhcpd.service ${D}${systemd_unitdir}/system/
}

PACKAGES =+ "${PN}-klogd"
FILES_${PN}-klogd += "${systemd_unitdir}/system/busybox-klogd.service"
FILES_${PN}-syslog += "${systemd_unitdir}/system/busybox-syslog.service"

RDEPENDS_${PN}-syslog += "${PN}-klogd"
