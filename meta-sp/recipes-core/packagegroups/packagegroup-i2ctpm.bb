SUMMARY = "packages to use i2c-tpm in embedded device"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit packagegroup

RDEPENDS_${PN} = "\
	ii2c eni2ctpm \
	trousers tpm-tools \
	openvpn openvpn-server openvpn-client easy-rsa \
"
