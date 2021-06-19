require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-core2-64-qemux86-64-toolchain-ext-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.8"
SRC_URI[sha256sum] = "da98120c0b3e1219813d6e3025d3452fab2b4f07eb68b6be4cbd70dabcc18569"

