FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-denx:"
SRC_URI_append = "file://boot_image_fit_when_boot_fdt_is_no.patch \
	    "
