FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE = "raspberrypi2"

SRC_URI += "file://rpi2_enable_tpm.patch"
