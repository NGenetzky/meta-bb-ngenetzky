require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-core2-64-qemux86-64-toolchain-ext-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.5"
SRC_URI[sha256sum] = "bffec99e237c6b95c29f35da4f7efb3d680fd73c03b0d6d6aef6b761a0af5127"
