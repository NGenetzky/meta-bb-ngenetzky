require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-ext-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.5"
SRC_URI[sha256sum] = "1eec58ae9116985b40566c69e8496878f1cc39efed3df7d9387e2074504f71e3"
