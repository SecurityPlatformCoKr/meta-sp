DESCRIPTION="easy-rsa is a CLI utility to build and manage a PKI CA"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=bc50580af64334feaf532250b8d12631"

SRC_URI="git://github.com/OpenVPN/easy-rsa.git;protocol=https \
	 file://create_tpm_key.patch \
	"
SRCREV="bc586578b4d6318e1a00317e5a14a19311649eac"

inherit allarch

S="${WORKDIR}/git"

do_compile() {
    ./build/build-dist.sh --version=${PV}
}

do_install() {
    mkdir -p ${WORKDIR}/tar
    tar zxf ${WORKDIR}/git/EasyRSA-${PV}.tgz  -C ${WORKDIR}/tar
    install -d ${D}/secure/${PN}
    install -d ${D}/secure/${PN}/doc
    install ${WORKDIR}/tar/EasyRSA-${PV}/ChangeLog ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/COPYING ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/easyrsa ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/openssl-1.0.cnf ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/vars.example ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/README.quickstart.md ${D}/secure/${PN}
    install ${WORKDIR}/tar/EasyRSA-${PV}/doc/* ${D}/secure/${PN}/doc
    install -d ${D}/secure/${PN}/Licensing
    install ${WORKDIR}/tar/EasyRSA-${PV}/Licensing/* ${D}/secure/${PN}/Licensing
    install -d ${D}/secure/${PN}/x509-types
    install ${WORKDIR}/tar/EasyRSA-${PV}/x509-types/* ${D}/secure/${PN}/x509-types
}

FILES_${PN}-doc="/secure/${PN}/doc/* \
		 /secure/${PN}/README.quickstart.md \
		 /secure/${PN}/ChangeLog \
		 /secure/${PN}/COPYING \
		 /secure/${PN}/Licensing/* \
		"
FILES_${PN}="/secure/${PN}/*"
RDEPENDS_${PN} += "openssl"
