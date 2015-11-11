DESCRIPTION="/secure of type ecryptfs Mounter"

SRC_URI="file://mount-secure.service \
	 file://mount-secure.sh \
	 "

inherit allarch

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

do_install() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/mount-secure.service ${D}${systemd_unitdir}/system
    install -d ${D}/usr/sbin/
    install -m 0755 ${WORKDIR}/mount-secure.sh ${D}/usr/sbin/
    install -d ${D}/secure
}

FILES_${PN} += "/usr/sbin/mount-secure.sh \
		${systemd_unitdir}/system/mount-secure.service \
		/secure \
		"

inherit systemd

SYSTEMD_SERVICE_${PN} = "mount-secure.service"

RDEPENDS_${PN} += "ecryptfs-utils"
