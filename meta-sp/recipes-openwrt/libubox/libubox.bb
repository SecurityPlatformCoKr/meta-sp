DESCRIPTION="freecwmp`s dependency"
LICENSE="CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/CC-BY-NC-SA-3.0;md5=b285975b5e439d99c95bcba5b5a8cf39"

SRC_URI="git://nbd.name/luci2/libubox.git;protocol=git \
	 file://cmake_soversion.patch \
	"
SRCREV="e6376a0eecfd8df8ce992d2b90f93e1a0828c06e"

S="${WORKDIR}/git"

DEPENDS += "json-c"

inherit cmake

EXTRA_OECMAKE="-DLUAPATH=/usr/lib/lua"

FILES_${PN} += "/usr/lib/lua/uloop.so"
FILES_${PN}-dbg += "/usr/lib/lua/.debug/uloop.so"
