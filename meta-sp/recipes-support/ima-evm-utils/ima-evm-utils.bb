DESCRIPTION="Integrity Measurement Architecture to know EXACTLY what has been run on your machine"

LICENSE="GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI="git://git.code.sf.net/p/linux-ima/ima-evm-utils;protocol=http"
SRCREV="3d9bdc1de282846de3523fd7a698d473304650b0"

S="${WORKDIR}/git"

inherit autotools

DEPENDS = "attr keyutils"
