DESCRIPTION="freecwmp`s dependency"
LICENSE="CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/CC-BY-NC-SA-3.0;md5=b285975b5e439d99c95bcba5b5a8cf39"

SRC_URI="git://dev.libfreecwmp.org/libfreecwmp;protocol=git \
	file://fix_soversion.patch \
	"

SRCREV="d2fdd97d66fde14859c06228a922066d9e8b669b"

S="${WORKDIR}/git"

inherit cmake
