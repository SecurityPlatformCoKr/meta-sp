SUMMARY = "tss in js"
SECTION = "tpm"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "node-gyp-native"

SRC_URI = "git://github.com/SecurityPlatformCoKr/node-tss.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

do_configure() {
    export LD="${CXX}"
    export GYP_DEFINES="sysroot=${STAGING_DIR_HOST}"
    node-gyp --arch ${TARGET_ARCH} configure
}

do_compile() {
    export LD="${CXX}"
    export GYP_DEFINES="sysroot=${STAGING_DIR_HOST}"
    node-gyp --arch ${TARGET_ARCH} configure
}

do_install() {
    install -d ${D}${libdir}/nodejs
    install ${S}/build/Release/node-tss.node ${D}${libdir}/nodejs/node-tss.node
#    install ${S}/src/palmbus.js ${D}${libdir}/nodejs/palmbus.js
}

RDEPENDS_${PN} += "libtspi"

FILES_${PN} += "${libdir}/nodejs"
FILES_${PN}-dbg += "${libdir}/nodejs/.debug"

