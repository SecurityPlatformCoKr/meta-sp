DESCRIPTION="easy-rsa is a CLI utility to build and manage a PKI CA"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=bc50580af64334feaf532250b8d12631"

SRC_URI="git://github.com/OpenVPN/easy-rsa.git;protocol=https"
SRCREV="bc586578b4d6318e1a00317e5a14a19311649eac"

S="${WORKDIR}/git"

do_compile() {
    ./build/build-dist.sh --version=${PV}
}

do_install() {
    install -d ${D}/usr/share/
    tar zxf ${WORKDIR}/git/EasyRSA-${PV}.tgz -C ${D}/usr/share/
    mv ${D}/usr/share/EasyRSA-${PV} ${D}/usr/share/${PN}
}

FILES_${PN}-doc="/usr/share/${PN}/doc/* \
		 /usr/share/${PN}/README.quickstart.md \
		 /usr/share/${PN}/ChangeLog \
		 /usr/share/${PN}/COPYING \
		 /usr/share/${PN}/Licensing/* \
		"
FILES_${PN}="/usr/share/${PN}/*"
