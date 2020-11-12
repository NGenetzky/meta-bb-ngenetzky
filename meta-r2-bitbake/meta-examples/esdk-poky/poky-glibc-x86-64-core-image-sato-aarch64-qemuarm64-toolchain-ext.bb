require yocto-poky-sdk.inc
PR = "r2"

SRCFILENAME = "poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-ext-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"

PV = "3.1.1"
SRC_URI[sha256sum] = "6e6b5705a755e6ce325b601a203688c0d80be262b16313e1b6c457e747f63b9f"
