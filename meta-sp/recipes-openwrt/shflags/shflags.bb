DESCRIPTION="Shell Flags (shFlags) is a library written to greatly simplify the handling of command-line flags in Bourne based Unix shell scripts (bash, dash, ksh, sh, zsh) on many Unix OSes (Linux, Solaris, Mac OS X, etc.). "
LICENSE="LGPL-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/LGPL-2.0;md5=9427b8ccf5cf3df47c29110424c9641a"

SRC_URI="svn://shflags.googlecode.com/svn;module=trunk;protocol=http"
SRCREV="191"

S="${WORKDIR}/trunk"

do_install() {
    mkdir -p ${D}/usr/share/shflags
    install -m 0755 ${S}/source/1.0/lib/shflags ${D}/usr/share/shflags/shflags.sh
}

FILES_${PN} += "/usr/share/shflags/shflags.sh"
