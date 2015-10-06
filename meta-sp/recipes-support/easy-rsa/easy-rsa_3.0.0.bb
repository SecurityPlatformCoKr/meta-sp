DESCRIPTION="easy-rsa is a CLI utility to build and manage a PKI CA"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=bc50580af64334feaf532250b8d12631"

SRC_URI="git://github.com/OpenVPN/easy-rsa.git;protocol=https \
	 file://create_tpm_key.patch \
	"
SRCREV="bc586578b4d6318e1a00317e5a14a19311649eac"

S="${WORKDIR}/git"

do_compile() {
    ./build/build-dist.sh --version=${PV}
}

do_install() {
    install -d ${D}/secure/${PN}
    mkdir ${WORKDIR}/tar
    tar zxf ${WORKDIR}/git/EasyRSA-${PV}.tgz  -C ${WORKDIR}/tar
    mv ${WORKDIR}/tar/EasyRSA-${PV}/* ${D}/secure/${PN}
    rmdir ${WORKDIR}/tar/EasyRSA-${PV}
    rmdir ${WORKDIR}/tar
}

FILES_${PN}-doc="/secure/${PN}/doc/* \
		 /secure/${PN}/README.quickstart.md \
		 /secure/${PN}/ChangeLog \
		 /secure/${PN}/COPYING \
		 /secure/${PN}/Licensing/* \
		"
FILES_${PN}="/secure/${PN}/*"
