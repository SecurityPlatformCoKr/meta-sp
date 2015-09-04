SUMMARY=""
DESCRIPTION=""
SECTION="tpm"

PR = "r0"
LICENSE = "CPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bd4ffd842ba5784fc7bc7d82bc901c5b"

DEPENDS = "openssl trousers"
SRC_URI += "http://downloads.sourceforge.net/project/opencryptoki/opencryptoki/${PV}/${PN}-v${PV}.tgz \
	file://host_include_not_allowed.patch \
	file://fix_wellknown_processing.patch \
	"
#	file://pkcs11_startup.patch 
SRC_URI[md5sum] = "df6a738460ac6be657de72abf4fcf21c"
SRC_URI[sha256sum] = "9e056f520147f1e55fa9ab9661b4a7b8589d9b13fa3bb1f122c6a0a79d416bb5"

inherit autotools gettext useradd systemd

S = "${WORKDIR}/${PN}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "pkcs11"

SYSTEMD_SERVICE_${PN} = "pkcsslotd.service"

PACKAGES =+ "lib${PN}-tpm     lib${PN}-cca     lib${PN}-sw \
	    "

FILES_${PN} += " \
	${sbindir}/* \
	${libdir}/libpencryptoki.* \
	${libdir}/opencryptoki/ \
	${libdir}/opencryptoki/libopencryptoki.* \
	${libdir}/pkcs11/ \
	${libdir}/pkcs11/methods \
	${libdir}/pkcs11/stdll \
	${libdir}/pkcs11/PKCS11_API.so \
	${systemd_unitdir}/system/pkcsslotd.service \
	${libdir}/opencryptoki/stdll \
	"

FILES_lib${PN}-sw += " \
	${libdir}/opencryptoki/stdll/libpkcs11_sw.so.0.0.0 \
	${libdir}/opencryptoki/stdll/libpkcs11_sw.so.0 \
	/run/lock/opencryptoki/swtok \
	"

FILES_lib${PN}-cca += " \
	${libdir}/opencryptoki/stdll/libpkcs11_cca.so.0.0.0 \
	${libdir}/opencryptoki/stdll/libpkcs11_cca.so.0 \
	/run/lock/opencryptoki/ccatok \
	"

FILES_${PN}-dev += " \
	${libdir}/pkcs11/PKCS11_API.so \
	${libdir}/pkcs11/libopencryptoki.so \
	${libdir}/opencryptoki/PKCS11_API.so \
	${libdir}/opencryptoki/libopencryptoki.so \
	${libdir}/opencryptoki/stdll/ \
	${libdir}/opencryptoki/stdll/*.la \
	"
FILES_lib${PN}-tpm += " \
	${libdir}/opencryptoki/stdll/libpkcs11_tpm.so.0.0.0 \
	${libdir}/opencryptoki/stdll/libpkcs11_tpm.so.0 \
	/run/lock/opencryptoki/tpm \
	"

FILES_${PN}-dbg += " \
	${libdir}/opencryptoki/stdll/.debug \
	/etc/rc.d* \
	"
RDEPENDS_lib${PN}-tpm  += "opencryptoki"
RDEPENDS_lib${PN}-sw  += "opencryptoki"
RDEPENDS_lib${PN}-cca  += "opencryptoki"

EXTRA_OECONF += "--with-systemd=/lib/systemd/system/"

do_unpack() {
    tar zxf ${DL_DIR}/${PN}-v${PV}.tgz -C ${WORKDIR}
}

do_configure_prepend() {
    sh bootstrap.sh
}

pkg_postinst_${PN} () {
/bin/grep -q include /etc/ld.so.conf || /bin/echo 'include /etc/ld.so.conf.d/*.conf' >> /etc/ld.so.conf && /sbin/ldconfig
}

pkg_postinst_lib${PN}-tpm () {
    ln -sf libpkcs11_tpm.so.0.0.0 /usr/lib/opencryptoki/stdll/libpkcs11_tpm.so
    ln -sf libpkcs11_tpm.so /usr/lib/opencryptoki/stdll/PKCS11_TPM.so
}
pkg_postinst_lib${PN}-sw () {
    ln -sf libpkcs11_sw.so.0.0.0 /usr/lib/opencryptoki/stdll/libpkcs11_sw.so
    ln -sf libpkcs11_sw.so /usr/lib/opencryptoki/stdll/PKCS11_TPM.so
}
pkg_postinst_lib${PN}-cca () {
    ln -sf libpkcs11_cca.so.0.0.0 /usr/lib/opencryptoki/stdll/libpkcs11_cca.so
    ln -sf libpkcs11_cca.so /usr/lib/opencryptoki/stdll/PKCS11_TPM.so
}
