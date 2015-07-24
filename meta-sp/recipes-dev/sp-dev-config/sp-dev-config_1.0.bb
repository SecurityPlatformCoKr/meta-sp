DESCRIPTION="initial configurations for sp-dev stage"

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://opkg.sp.conf \
	   file://sp-first-install.sh \
	  "

do_install() {
    install -d ${D}${sysconfdir}/opkg
    install -m 0644 ${WORKDIR}/opkg.sp.conf ${D}${sysconfdir}/opkg/sp.conf
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/sp-first-install.sh ${D}${bindir}
}

FILES_${PN} = "/etc/opkg/sp.conf \
	       ${bindir}/sp-first-install.sh \
	      "
