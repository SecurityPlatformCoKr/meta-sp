DESCRIPTION="freecwmp`s dependency"
LICENSE="CC-BY-NC-SA-3.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/CC-BY-NC-SA-3.0;md5=b285975b5e439d99c95bcba5b5a8cf39"

SRC_URI="git://dev.freecwmp.org/zstream-clone \
	"
SRCREV="3b9748999aa3d81a5811eff1cc221266a2588e28"

S="${WORKDIR}/git"

do_compile() {
    make all
}

do_install() {
    make DESTDIR=${D} install
}
