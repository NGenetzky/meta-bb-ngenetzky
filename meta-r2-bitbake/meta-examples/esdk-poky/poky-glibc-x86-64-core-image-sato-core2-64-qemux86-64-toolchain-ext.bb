require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-core2-64-qemux86-64-toolchain-ext-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.5"
SRC_URI[sha256sum] = "2e2386a68471c325ec3039472ba1c330d6c31e92f27d85384064cde1d56f0a64"
