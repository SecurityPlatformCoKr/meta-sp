SUMMARY=""
DESCRIPTION=""
SECTION="tpm"

PR = "r0"
LICENSE = "CPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bd4ffd842ba5784fc7bc7d82bc901c5b"

DEPENDS = "openssl"
SRC_URI += "http://downloads.sourceforge.net/project/opencryptoki/opencryptoki/${PV}/${PN}-v${PV}.tgz \
	file://host_include_not_allowed.patch \
	"
#	file://pkcs11_startup.patch 
SRC_URI[md5sum] = "9c3b3ff3d935e09bfa132f2951d4c859"
SRC_URI[sha256sum] = "d0b4676766753449f4d9001436cf8371812ddff7b59869e8d5adef94c4fd261b"

inherit autotools gettext useradd systemd

S = "${WORKDIR}/${PN}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "pkcs11"

SYSTEMD_SERVICE_${PN} = "pkcsslotd.service"

PACKAGES =+ "lib${PN}-tpm lib${PN}-cca lib${PN}-sw \
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
	"

FILES_lib${PN}-cca += " \
	${libdir}/opencryptoki/stdll/libpkcs11_cca.so.0.0.0 \
	${libdir}/opencryptoki/stdll/libpkcs11_cca.so.0 \
	"

FILES_${PN}-dev += " \
	/usr/lib/pkcs11/PKCS11_API.so \
	/usr/lib/pkcs11/libopencryptoki.so \
	/usr/lib/opencryptoki/PKCS11_API.so \
	/usr/lib/opencryptoki/libopencryptoki.so \
	/usr/lib/opencryptoki/stdll/ \
	/usr/lib/opencryptoki/stdll/*.la \
	/usr/lib/opencryptoki/stdll/libpkcs11_sw.so \
	/usr/lib/opencryptoki/stdll/PKCS11_SW.so \
	/usr/lib/opencryptoki/stdll/libpkcs11_cca.so \
	/usr/lib/opencryptoki/stdll/PKCS11_CCA.so \
	${libdir}/opencryptoki/stdll/libpkcs11_tpm.so \
	${libdir}/opencryptoki/stdll/PKCS11_TPM.so \
	"

FILES_lib${PN}-tpm += " \
	${libdir}/opencryptoki/stdll/libpkcs11_tpm.so.0.0.0 \
	${libdir}/opencryptoki/stdll/libpkcs11_tpm.so.0 \
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
