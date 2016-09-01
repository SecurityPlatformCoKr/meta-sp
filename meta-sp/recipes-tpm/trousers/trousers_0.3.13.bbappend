FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd
SYSTEMD_SERVICE_${PN} = "trousers.service"

SRC_URI += " file://add_dev_tpm1.patch \
	     file://trousers.service \
	    file://002_remove_misguided_attempt_to_free_memory_in_tspi_context_close.patch \
	    "
PR="r1"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/trousers.service ${D}${systemd_unitdir}/system
    rm -f ${D}${systemd_unitdir}/system/tcsd.service
    rmdir ${D}${libdir}/trousers
}
SYSTEMD_AUTO_ENABLE = "enable"

# r1: Remove misguided attempt to free memory in Tspi_Context_Close()
