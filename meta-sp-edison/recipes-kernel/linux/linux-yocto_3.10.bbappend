FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE = "edison"
LINUX_VERSION = "3.10.17"
SRC_URI += "file://tpm_i2c_atmel.patch \
	    file://sp-kernel-module.patch \
	    "

do_configure_append() {
    make i386_edison_defconfig
}
