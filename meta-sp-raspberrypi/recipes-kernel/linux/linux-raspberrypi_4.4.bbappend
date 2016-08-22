FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
COMPATIBLE_MACHINE = "raspberrypi[23]"

SRC_URI += "file://recognize_tpm_i2c.patch \
	    file://config.patch \
	    "
KERNEL_DEFCONFIG_raspberrypi3 ?= "bcm2709_defconfig"

OVERRIDES="${MACHINE}"
KERNEL_DEVICETREE_raspberrypi2 =+ "bcm2709-rpi-2-b-tpm.dtb"
KERNEL_DEVICETREE_raspberrypi3 =+ "bcm2710-rpi-3-b-tpm.dtb"

DEPENDS += "u-boot"

do_sign_kernel() {
    POKYDIR="${TOPDIR}/../poky"
    KEYDIR="${POKYDIR}/meta-iot-black/keys"
    if [ -e "${KEYDIR}/dev.key" ]; then
	UBOOTMKIMAGE=`find ${TOPDIR}/tmp/work/${MACHINE}-poky-linux-gnueabi/u-boot -name mkimage | head -1`
	BUILDDIR="${WORKDIR}/linux-${MACHINE}-standard-build"
	IMAGEFILE="${BUILDDIR}/arch/${ARCH}/boot/zImage"
	METASPRPI="${POKYDIR}/meta-iot-black/meta-sp-raspberrypi"
	KERNEL_DEVICETREE=${KERNEL_DEVICETREE_${MACHINE}}
	rm -rf "${TOPDIR}/signKernel"
	mkdir -p "${TOPDIR}/signKernel"
	cp -f ${IMAGEFILE} ${TOPDIR}/signKernel/
	cp -f ${METASPRPI}/utils/sign/sign-rpi2.its ${TOPDIR}/signKernel/sign.its
	ls ${BUILDDIR}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE}
	cp -f ${BUILDDIR}/arch/${ARCH}/boot/dts/${KERNEL_DEVICETREE} ${TOPDIR}/signKernel/rpi-tpm.dtb
	cp -f ${METASPRPI}/recipes-bsp/u-boot/files/bcm2709-rpi-2-b-pubkey.dtb ${TOPDIR}/signKernel/
	cd ${TOPDIR}/signKernel
	${UBOOTMKIMAGE} -f sign.its -K bcm2709-rpi-2-b-pubkey.dtb -k ${KEYDIR} -r image.fit
	cp image.fit ${BUILDDIR}/arch/${ARCH}/boot/uImage
	cd -
    fi
}

do_sign_kernel[doc] = "generate image.fit signed with meta-iot-black/keys/dev.key"
addtask sign_kernel before do_rpiboot_mkimage after do_compile
