DESCRIPTION="A simple library for using the TPM chip to secure SSH keys."

SRC_URI="git://github.com/ThomasHabets/simple-tpm-pk11.git;protocol=https \
	 file://ssh.config \
	"
SRCREV="7d27c9c8b0843dcc1b95b85c48edf0f125a091ae"

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://LICENSE;md5=39e1216ae8010a1db218af17ecd5f474"

S="${WORKDIR}/git"

inherit autotools

do_configure_prepend() {
    sh bootstrap.sh
}

do_install_append() {
    install -d ${D}/root/.ssh
    install -m 0600 ${WORKDIR}/ssh.config ${D}/root/.ssh/config
}

FILES_${PN} += "/root/.ssh/config \
		"
