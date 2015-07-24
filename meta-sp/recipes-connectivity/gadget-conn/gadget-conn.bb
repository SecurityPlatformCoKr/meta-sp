SUMMARY = "Connman config to setup gadget interface on boot-up process"
DESCRIPTION = "This is the ConnMan configuration to set up a Gadget Interface \
on boot-up process."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://gadget-conn-udhcpd.service \
           file://g_ether.modprobe.conf \
           file://g_ether.modules-load.conf \
	   file://90-usb0.rules \
	   file://udhcpd.conf \
	   file://usb0.sh \
          "
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES += "${PN}-udhcpd"

FILES_${PN} =  "/etc/modules-load.d/g_ether.conf \
		/etc/modprobe.d/g_ether.conf \
		/etc/udev/rules.d/90-usb0.rules \
		/usr/bin/usb0.sh \
		"
FILES_${PN}-udhcpd = "${systemd_unitdir}/system/gadget-conn-udhcpd.service \
		      /etc/udhcpd.conf \
		"

SYSTEMD_SERVICE_${PN}-udhcpd = "gadget-conn-udhcpd.service"

inherit systemd

do_install() {
    install -d ${D}/etc/modules-load.d/
    install -m 0644 ${WORKDIR}/g_ether.modules-load.conf ${D}/etc/modules-load.d/g_ether.conf
    install -d ${D}/etc/modprobe.d/
    install -m 0644 ${WORKDIR}/g_ether.modprobe.conf ${D}/etc/modprobe.d/g_ether.conf

    install -d ${D}/etc/udev/rules.d/
    install -m 0644 ${WORKDIR}/90-usb0.rules ${D}/etc/udev/rules.d/
    install -d ${D}/usr/bin
    install -m 0644 ${WORKDIR}/usb0.sh ${D}/usr/bin

    install -d ${D}/lib/systemd/system
    install -m 0644 ${WORKDIR}/gadget-conn-udhcpd.service ${D}/lib/systemd/system/
    install -m 0644 ${WORKDIR}/udhcpd.conf ${D}/etc/
}

RDEPENDS_${PN} = "connman-client \
                 kernel-module-bridge \
                 kernel-module-g-ether \
		 fixmac \
		 "

RDEPENDS_${PN}-udhcpd = "busybox \
			"
