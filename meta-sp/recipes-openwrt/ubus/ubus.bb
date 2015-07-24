DESCRIPTION="freecwmp`s dependency"
LICENSE="CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/CC-BY-NC-SA-3.0;md5=b285975b5e439d99c95bcba5b5a8cf39"

SRC_URI="git://nbd.name/luci2/ubus.git;protocol=git \
	file://add_service_unit_install.patch \
	file://cmake_soversion.patch \
	"
SRCREV="f361bfa5fcb2daadf3b160583ce665024f8d108e"

S="${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE="-DLUAPATH=/usr/lib/lua"

inherit systemd

SYSTEMD_SERVICE_${PN} = "ubus.service"

FILES_${PN} += "${systemd_unitdir}/system/ubus.socket \
		${systemd_unitdir}/system/ubus.service \
		"

PACKAGES =+ "lib${PN}-lua"

FILES_lib${PN}-lua += "/usr/lib/lua/ubus.so"
FILES_${PN}-dbg += "/usr/lib/lua/.debug/ubus.so"

do_install_append() {
    mkdir -p ${D}/lib
    mv ${D}/usr/lib/systemd ${D}/lib/
}
