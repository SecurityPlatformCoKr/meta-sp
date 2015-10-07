DESCRIPTION="This package contains 2 sets of code, a command-line utility used to generate a TSS key blob and write it to disk and an OpenSSL engine which interfaces with the TSS API."

LICENSE="OpenSSL"
LIC_FILES_CHKSUM="file://LICENSE;md5=11f0ee3af475c85b907426e285c9bb52"

SRC_URI="http://downloads.sourceforge.net/project/trousers/OpenSSL%20TPM%20Engine/0.4.2/openssl_tpm_engine-0.4.2.tar.gz \
	file://wellknown.patch \
	"
SRC_URI[md5sum] = "5bc8d66399e517dde25ff55ce4c6560f"
SRC_URI[sha256sum] = "2df697e583053f7047a89daa4585e21fc67cf4397ee34ece94cf2d4b4f7ab49c"

S="${WORKDIR}/openssl_tpm_engine-${PV}"

DEPENDS += "openssl trousers"

inherit autotools

do_configure_prepend() {
    touch ${S}/NEWS
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

do_compile_append() {
    make -C test
}

do_install_append() {
    mv ${D}/usr/lib/openssl/engines ${D}/usr/lib/
    rmdir ${D}/usr/lib/openssl
    install -d ${D}${bindir}
    install -m 0755 test/engine_key_loading ${D}${bindir}
    install -d ${D}/usr/share/openssl-tpm-engine
    install -m 0755 ${S}/test/stunnel_connect.sh ${D}/usr/share/openssl-tpm-engine/
    install -m 0755 ${S}/test/gentpmcert.sh ${D}/usr/share/openssl-tpm-engine/
}

PACKAGES =+ "${PN}-test"

FILES_${PN} += "/usr/lib/engines/libtpm.so.0.0.0 \
		/usr/lib/engines/libtpm.so.0 \
		/usr/lib/engines/libtpm.so \
		"
INSANE_SKIP_${PN} += "dev-so"
FILES_${PN}-dev += " \
		    /usr/lib/engines/libtpm.la \
		    "
FILES_${PN}-dbg += "/usr/lib/engines/.debug/*"
FILES_${PN}-test += "/usr/bin/engine_key_loading \
		     /usr/share/openssl-tpm-engine/stunnel_connect.sh \
		     /usr/share/openssl-tpm-engine/gentpmcert.sh \
		    "
RDEPENDS_${PN} += "openssl"
