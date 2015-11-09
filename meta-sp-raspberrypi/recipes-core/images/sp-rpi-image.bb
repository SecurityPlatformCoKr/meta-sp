#include recipes-tpm/images/core-image-tpm.inc

DESCRIPTION = "Image with Trousers daemon." 

IMAGE_FEATURES += "ssh-server-openssh"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-boot \
	packagegroup-tpm \
	kmod \
	udev-extraconf \
"

IMAGE_INSTALL += "i2c-tools \
		  sp-i2c-loader \
		 "
