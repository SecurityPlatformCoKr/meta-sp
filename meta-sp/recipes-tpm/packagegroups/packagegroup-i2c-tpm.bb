DESCRIPTION = "Basic packagegroup for TCS TSS and utilities that use it."
LICENSE = "MIT"
PR = "r0"

inherit packagegroup

PROVIDES = "packagegroup-tpm"

RDEPENDS_packagegroup-i2c-tpm = "\
    trousers \
    tpm-tools \
    "
