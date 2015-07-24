DESCRIPTION="freecwmp`s dependency"
LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=00d970f8d9ffc8dda61f8c387779476b"

DEPENDS = "libmicroxml uci ubus libubox libzstream"

SRC_URI="git://dev.freecwmp.org/freecwmp \
	 file://20121219_lib_config_uci.sh \
	 file://20121219_lib_functions_network.sh \
	 file://20121219_lib_functions.sh \
	 file://fix_k2m.patch \
	 file://freecwmpd.service \
	"
SRCREV="8f3c163fc85337e63bfa64da3c02f10d1fe3b169"

S="${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE="-DACS=multi -DDEBUG=ON -DDEVEL=ON" 

do_install_append() {
    mkdir -p ${D}/usr/sbin/
    install -m 0755 ${S}/ext/openwrt/scripts/freecwmp.sh ${D}/usr/sbin/freecwmp
    mkdir -p ${D}/usr/share/freecwmp/functions
    install -m 0644 ${S}/ext/openwrt/scripts/defaults ${D}/usr/share/freecwmp/
    install -m 0644 ${S}/ext/openwrt/scripts/functions/* ${D}/usr/share/freecwmp/functions
    mkdir -p ${D}/etc/config
    install -m 0644 ${S}/ext/openwrt/config/freecwmp ${D}/etc/config

    mkdir -p ${D}/lib/functions
    mkdir -p ${D}/lib/config
    install -m 0755 ${WORKDIR}/20121219_lib_functions_network.sh ${D}/lib/functions/network.sh
    install -m 0755 ${WORKDIR}/20121219_lib_functions.sh ${D}/lib/functions.sh
    install -m 0755 ${WORKDIR}/20121219_lib_config_uci.sh ${D}/lib/config/uci.sh

    mkdir -p ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/freecwmpd.service ${D}/${systemd_unitdir}/system/
}

inherit systemd

FILES_${PN} +=  "/usr/share/freecwmp/* \
		 /usr/sbin/freecwmp \
		 /lib/functions/network.sh \
		 /lib/functions.sh \
		 /lib/config/uci.sh \
		 ${systemd_unitdir}/system/freecwmpd.service \
		"

CONFFILES_${PN} += "/etc/config/freecwmp \
		    "
SYSTEMD_SERVICE_${PN} = "freecwmpd.service"

RDEPENDS_${PN} += "shflags ubus libmicroxml uci libubox libzstream"
