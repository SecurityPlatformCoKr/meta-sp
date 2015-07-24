DESCRIPTION="The Google Authenticator project includes implementations of one-time passcode generators for several mobile platforms, as well as a pluggable authentication module (PAM)."
SECTION = "libs"

LICENSE="Apache-2.0"
LIC_FILES_CHKSUM="file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI="git://github.com/google/google-authenticator.git;protocol=https"
SRCREV="f0d1574734b5855d4a604d58be25fc1159563b66"

DEPENDS += "libpam"

S="${WORKDIR}/git/libpam"

inherit autotools

EXTRA_OECONF += "--libdir=/lib"
do_configure_prepend() {
    ./bootstrap.sh
}

PACKAGES += "lib${PN}"

FILES_lib${PN} += "/lib/security/pam_google_authenticator.so"
FILES_${PN}-dbg +=  " /lib/security/pam_google_authenticator.la \
		    /lib/security/.debug/* \
		    "

RDEPENDS_${PN} += "lib${PN}"
