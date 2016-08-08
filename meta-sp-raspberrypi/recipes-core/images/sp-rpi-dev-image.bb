DESCRIPTION = "SP DEV Image" 

IMAGE_FEATURES += "package-management ssh-server-openssh"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL += "\
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-core-boot \
	packagegroup-i2c-tpm \
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
		git libcurl gdbm libgmpxx \
		gnutls-openssl gnutls-xx \
		python-re python-readline vim \
		 "

# for debugging
IMAGE_INSTALL += " strace \
		"

# for dev
IMAGE_INSTALL += " attr-dev base-files-dev base-passwd-dev \
		bison-dev bzip2-dev coreutils-dev cracklib-dev \
		cryptodev-linux-dev curl-dev db-dev flex-dev \
		libcap-dev libffi-dev gnutls-dev libpam-dev \
		libtspi-dev zlib-dev m4-dev ncurses-dev nettle-dev \
		openssl-dev python-dev shadow-dev shadow-securetty-dev \
		"
