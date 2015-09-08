PV="2014.07-1"
S="${WORKDIR}/git"
PACKAGE_ARCH="${MACHINE_ARCH}"
SRC_URI = "git://git.denx.de/u-boot.git;tag=v2014.07 \
	    file://edison-u-boot-v2014.07.patch \
	    file://${MACHINE}.env \
	    file://target_env/*.env \
	    "

