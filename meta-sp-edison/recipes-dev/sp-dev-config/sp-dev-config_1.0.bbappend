FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://opkg.sp.conf"

do_install_append() {
    install -d ${D}${sysconfdir}/opkg
    install -m 0600 ${WORKDIR}/opkg.sp/conf ${D}${sysconfdir}/opkg/sp.conf

    sed -i -e "s/@SPSyslogServer@/192.168.2.16/g" ${bindir}/sp-first-install.sh
}

FILES_${PN} += "${sysconfdir}/opkg/sp.conf"
