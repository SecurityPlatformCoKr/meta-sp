DESCRIPTION="uHTTPd is a web server written from scratch by OpenWrt/LuCI developers. It is aimed towards being an efficient and stable server, suitable for lightweight tasks commonly used with embedded devices and proper integration with OpenWrt's configuration framework (UCI)."

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI="svn://svn.openwrt.org/openwrt/branches/backfire/package;protocol=svn;module=uhttpd;rev=46134 \
	file://001-pass-env.patch \
	file://002-link-order.patch \
	file://fix_xopen_source.patch \
	file://uhttpd.service \
	"

S="${WORKDIR}/${PN}/src"

DEPENDS += "openssl"

do_compile() {
    make LUA_SUPPORT=0 TLS_SUPPORT=1 CGI_SUPPORT=1  UHTTPD_TLS=openssl
}

do_install() {
    mkdir -p ${D}/usr/sbin
    install -m 0755 ${S}/uhttpd ${D}/usr/sbin

    mkdir -p ${D}${libdir}/lua
    install -m 0755 ${S}/uhttpd_tls.so ${D}${libdir}
    ln -sf ${libdir}/lua ${D}${libdir}/lua/5.1

    mkdir -p ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/uhttpd.service ${D}${systemd_unitdir}/system/
}

PACKAGES =+ "${PN}-mod-tls"

RDEPENDS_${PN} += "lua5.1 luci-modules-admin-core"

inherit systemd

SYSTEMD_SERVICE_${PN} = "uhttpd.service"

FILES_${PN} += "${systemd_unitdir}/system/uhttpd.service \
	        ${libdir}/lua/5.1 \
	       "
FILES_${PN}-mod-tls += "${libdir}/uhttpd_tls.so"
