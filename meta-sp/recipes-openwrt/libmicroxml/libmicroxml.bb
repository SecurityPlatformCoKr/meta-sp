DESCRIPTION="freecwmp`s dependency"
LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=85aba3acd06d8e169419ed1a65e40ed8"

SRC_URI="git://dev.freecwmp.org/microxml;protocol=git"
SRCREV="72965423184f24cc0b963d91c2d1863cdb01b6aa"

S="${WORKDIR}/git"

inherit autotools

EXTRA_OECONF+="--enable-threads --enable-shared --enable-static"

do_configure() {
    autoconf -i   # no -f
    oe_runconf
}

do_install() {
    make DSTROOT=${D} install
}
