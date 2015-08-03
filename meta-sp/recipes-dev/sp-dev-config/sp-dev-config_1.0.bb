DESCRIPTION="initial configurations for sp-dev stage"

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " file://sp-first-install.sh \
	  "

do_install() {
    install -d ${D}${bindir}
    install -m 0700 ${WORKDIR}/sp-first-install.sh ${D}${bindir}
}

FILES_${PN} = " ${bindir}/sp-first-install.sh \
	      "
