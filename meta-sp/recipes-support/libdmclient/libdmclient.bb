DESCRIPTION="libdmclient is a communication library for mobile device management. It implements the client-side of OMA DM 1.2 protocol."

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://dmcore/COPYING;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI="git://github.com/01org/libdmclient.git;protocol=https \
	 file://tests_install_exec_hook.patch \
	"
SRCREV="9d4e3e8f252ea6e70f9b0b5f8e1fc733852d8c17"

DEPENDS = "curl sqlite"

inherit autotools
EXTRA_OECONF="--enable-tests"

PACKAGES =+ "${PN}-tests"
FILES_${PN}-tests = "${bindir}/testdmclient"

S="${WORKDIR}/git"
FILES_${PN}-dbg += "${bindir}/plugins/.debug/*"
