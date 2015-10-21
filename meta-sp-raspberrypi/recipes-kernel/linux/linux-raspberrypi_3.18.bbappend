FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE = "raspberrypi2"

SRC_URI += "file://sp_rpi2_tpm.patch \
	    file://recognize_tpm_i2c.patch \
	    "
