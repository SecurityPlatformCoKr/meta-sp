#nclude recipes-tpm/images/core-image-tpm.inc

DESCRIPTION = "SP Image" 

IMAGE_FEATURES += "package-management ssh-server-openssh"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-boot \
	packagegroup-tpm \
	kmod \
	udev-extraconf \
	linux-firmware \
	connman connman-client \
	e2fsprogs-mke2fs e2fsprogs-resize2fs \
"

# tpm related packages
IMAGE_INSTALL += "i2c-tools \
		  sp-i2c-loader \
		  ecryptfs-utils mount-secure \
		  openvpn-server openvpn-client easy-rsa \
		 "
# dev tools

IMAGE_INSTALL += "packagegroup-core-buildessential \
		 "

# for debugging
IMAGE_INSTALL += " strace \
		    sp-dev-config \
		"
