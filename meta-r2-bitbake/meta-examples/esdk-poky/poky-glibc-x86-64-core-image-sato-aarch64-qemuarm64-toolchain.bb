require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.5"
SRC_URI[sha256sum] = "09d450311221edab53f3c2647c44ab2f4e5dd502d8c60dba35aaadad94bf98af"
