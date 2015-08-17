SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=5aac200199fde47501876cba7263cb0c"
DEPENDS = "lzo openssl iproute2 ${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"
DEPENDS += "systemd"

inherit autotools

SRC_URI = "git://github.com/OpenVPN/openvpn.git;protocol=https;nobranch=1 \
	   file://check_tty_ifndef_systemd.patch \
           file://openvpn@.service \
	   file://server.conf \
	   file://client.conf \
	   "
SRCREV = "0e58729cceb5da24c38d22468cd0362ab16938ed"
S = "${WORKDIR}/git"

CFLAGS += "-fno-inline"

# I want openvpn to be able to read password from file (hrw)
EXTRA_OECONF += "--enable-password-save --enable-iproute2"
EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'pam', '', '--disable-plugin-auth-pam', d)}"
EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--enable-systemd', '', d)}"

# Explicitly specify IPROUTE to bypass the configure-time check for /sbin/ip on the host.
EXTRA_OECONF += "IPROUTE=/sbin/ip"
EXTRA_OECONF += "SYSTEMD_ASK_PASSWORD=/bin/systemd-ask-password"

do_install_append() {
    install -d ${D}/var/lib/openvpn
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/openvpn@.service ${D}${systemd_unitdir}/system/
    install -d ${D}${sysconfdir}/openvpn
    install -m 0644 ${WORKDIR}/server.conf ${D}${sysconfdir}/openvpn/
    install -m 0644 ${WORKDIR}/client.conf ${D}${sysconfdir}/openvpn/
}

inherit systemd

SYSTEMD_AUTO_ENABLE = "disable"

FILES_${PN}-dbg += "${libdir}/openvpn/plugins/.debug"
FILES_${PN} += "${systemd_unitdir}/system/openvpn@.service \
		/var/lib/openvpn \
		"

PACKAGES =+ "${PN}-server ${PN}-client"

FILES_${PN}-server = "${sysconfdir}/openvpn/server.conf"
FILES_${PN}-client = "${sysconfdir}/openvpn/client.conf"
CONFFILES_${PN}-server = "${sysconfdir}/openvpn/server.conf"
CONFFILES_${PN}-client = "${sysconfdir}/openvpn/client.conf"
