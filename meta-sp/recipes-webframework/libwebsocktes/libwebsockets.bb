DESCRIPTION = "Libwebsockets is a lightweight pure C library built to use minimal CPU and memory resources, and provide fast throughput in both directions"

LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/LGPL-2.0;md5=9427b8ccf5cf3df47c29110424c9641a"

SRC_URI += "git://git.libwebsockets.org/libwebsockets"
SRCREV = "3ae1badae7a05e0982e0dfbcb078da3d4b92a81d"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "openssl"

inherit cmake

PACKAGES += "${PN}-test-server"
RPROVIDES_${PN} += "${PN}-test-server"

FILES_${PN} = "/usr/lib/*"
FILES_${PN}-test-server = "/usr/share/libwebsockets-test-server/* \
			   /usr/bin/libwebsockets* "
