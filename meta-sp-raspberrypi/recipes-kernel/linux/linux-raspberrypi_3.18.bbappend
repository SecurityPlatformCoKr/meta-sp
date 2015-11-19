FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE = "raspberrypi2"

SRC_URI += " \
	    file://config.patch \
	    file://recognize_tpm_i2c.patch \
	    "
DEPENDS += "u-boot"

do_sign_kernel() {
    POKYDIR="${TOPDIR}/../poky"
    KEYDIR="${POKYDIR}/iot-black/keys"
    if [ -e "${KEYDIR}/dev.key" ]; then
	UBOOTMKIMAGE=`find ${TOPDIR}/tmp/work/raspberrypi2-poky-linux-gnueabi/u-boot -name mkimage | head -1`
	BUILDDIR="${WORKDIR}/linux-raspberrypi2-standard-build"
	IMAGEFILE="${BUILDDIR}/arch/${ARCH}/boot/zImage"
	METASPRPI="${POKYDIR}/iot-black/meta-sp-raspberrypi"
	rm -rf "${TOPDIR}/signKernel"
	mkdir -p "${TOPDIR}/signKernel"
	cp -f ${IMAGEFILE} ${TOPDIR}/signKernel/
	cp -f ${METASPRPI}/utils/sign/sign-rpi2.its ${TOPDIR}/signKernel/sign.its
	cp -f ${BUILDDIR}/arch/${ARCH}/boot/dts/bcm2709-rpi-2-b-tpm-infineon.dtb ${TOPDIR}/signKernel/
	cp -f ${BUILDDIR}/arch/${ARCH}/boot/dts/bcm2709-rpi-2-b-tpm-atmel.dtb ${TOPDIR}/signKernel/
	cp -f ${METASPRPI}/recipes-bsp/u-boot/files/bcm2709-rpi-2-b-pubkey.dtb ${TOPDIR}/signKernel/
	cd ${TOPDIR}/signKernel
	${UBOOTMKIMAGE} -f sign.its -K bcm2709-rpi-2-b-pubkey.dtb -k ${KEYDIR} -r image.fit
	cp image.fit ${BUILDDIR}/arch/${ARCH}/boot/uImage
	cd -
    fi
}

do_sign_kernel[doc] = "generate image.fit signed with iot-black/keys/dev.key"
addtask sign_kernel before do_rpiboot_mkimage after do_compile
