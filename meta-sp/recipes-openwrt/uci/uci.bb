DESCRIPTION="freecwmp`s dependency"
LICENSE="CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/CC-BY-NC-SA-3.0;md5=b285975b5e439d99c95bcba5b5a8cf39"

DEPENDS += "libubox"

SRC_URI="git://nbd.name/uci.git;protocol=git \
	 file://cmake_soversion.patch \
	 file://config.system \
	 file://config.network \
	 file://config.fstab \
	"
SRCREV="a6a745338cd004a88edaaace5ca145a4411cdda9"

S="${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE="-DLUAPATH=/usr/lib/lua"

do_install_append() {
    mv ${D}/usr/bin ${D}/sbin
    mkdir -p ${D}${sysconfdir}/config
    install -m 0644 ${WORKDIR}/config.system ${D}${sysconfdir}/config/system
    install -m 0644 ${WORKDIR}/config.network ${D}${sysconfdir}/config/network
    install -m 0644 ${WORKDIR}/config.fstab ${D}${sysconfdir}/config/fstab
}

PACKAGES += "lib${PN}-lua"
FILES_${PN} += "${sysconfdir}/config/system \
		${sysconfdir}/config/network  \
		${sysconfdir}/config/fstab  \
		"
FILES_lib${PN}-lua += "/usr/lib/lua/uci.so"
FILES_${PN}-dbg += "/usr/lib/lua/.debug/uci.so"
