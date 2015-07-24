DESCRIPTION="This package contains 2 sets of code, a command-line utility used to generate a TSS key blob and write it to disk and an OpenSSL engine which interfaces with the TSS API."

LICENSE="OpenSSL"
LIC_FILES_CHKSUM="file://LICENSE;md5=11f0ee3af475c85b907426e285c9bb52"

SRC_URI="http://downloads.sourceforge.net/project/trousers/OpenSSL%20TPM%20Engine/0.4.2/openssl_tpm_engine-0.4.2.tar.gz"
SRC_URI[md5sum] = "5bc8d66399e517dde25ff55ce4c6560f"
SRC_URI[sha256sum] = "2df697e583053f7047a89daa4585e21fc67cf4397ee34ece94cf2d4b4f7ab49c"

S="${WORKDIR}/openssl_tpm_engine-${PV}"

DEPENDS += "openssl"

inherit autotools

do_configure_prepend() {
    sh bootstrap.sh
}

do_install_append() {
    mv ${D}/usr/lib/openssl ${D}/usr/lib/ssl
}

FILES_${PN} += "/usr/lib/ssl/engines/libtpm.so.0.0.0 \
		/usr/lib/ssl/engines/libtpm.so.0 \
		"
FILES_${PN}-dev += "/usr/lib/ssl/engines/libtpm.so \
		    /usr/lib/ssl/engines/libtpm.la \
		    "
FILES_${PN}-dbg += "/usr/lib/ssl/engines/.debug/*"
RDEPENDS_${PN} += "openssl"
