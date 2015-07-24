DESCRIPTION = "a GPL multi-threaded proxy program for redirecting network socket connections to/from serial links, in cases where the remote end of the serial link doesn't have a TCP/IP stack (eg an embedded or microcontroller system)."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=361b6b837cad26c6900a926b62aada5f"

SRC_URI = "git://github.com/bewest/serwebproxy;protocol=https"
SRC_URI[md5sum] = "94f92b634d758235d1c9db2e29d86cd8"
SRC_URI[sha256sum] = "107bea515887685e248ef2e23176e0262720e3a34447109cbd026a271a4c98e2"
SRCREV="ef155874bf1f6679350848a67b78dd905c1d531e"

S="${WORKDIR}/git"

CFLAGS += "-D__UNIX__"

FILES_${PN} = "/usr/bin/serwebproxy"

do_install() {
    mkdir -p ${D}/usr/bin/
    install -m 0755 ${S}/serwebproxy ${D}/usr/bin
}
