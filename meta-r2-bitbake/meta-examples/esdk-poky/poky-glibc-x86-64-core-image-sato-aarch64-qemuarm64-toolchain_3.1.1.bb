require yocto-poky-sdk.inc

PV = "3.1.1"
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"
SRC_URI[sha256sum] = "2e2386a68471c325ec3039472ba1c330d6c31e92f27d85384064cde1d56f0a64"
