SUMMARY = "Yocto Project Application Development and the Extensible Software Development Kit (eSDK)"
HOMEPAGE = "https://www.yoctoproject.org/docs/3.1.1/sdk-manual/sdk-manual.html"

inherit bb_fetcher
addtask do_unpack before do_build

PV = "3.1.1"
SRCFILENAME = "poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-${PV}.sh"
SRC_URI = "https://downloads.yoctoproject.org/releases/yocto/yocto-${PV}/toolchain/x86_64/${SRCFILENAME}"
SRC_URI[sha256sum] = "2e2386a68471c325ec3039472ba1c330d6c31e92f27d85384064cde1d56f0a64"

# http://downloads.yoctoproject.org/releases/yocto/yocto-3.1.1/toolchain/x86_64/
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.host.manifest	48.4 KiB	2020-Jun-08 22:56
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.host.manifest.sha256sum	147 B	2020-Jun-18 17:26
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.sh	455.4 MiB	2020-Jun-08 22:56
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.sh.sha256sum	136 B	2020-Jun-18 17:26
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.target.manifest	56.7 KiB	2020-Jun-08 22:56
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.target.manifest.sha256sum	149 B	2020-Jun-18 17:26
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.testdata.json	249.6 KiB	2020-Jun-08 22:56
# poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.testdata.json.sha256sum	147 B	2020-Jun-18 17:26

YOCTO_SDK_DEST = "${WORKDIR}/poky_sdk/"

yocto_sdk_installer(){
    # ./poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.sh -h
    # Usage: poky-glibc-x86_64-core-image-sato-aarch64-qemuarm64-toolchain-3.1.1.sh [-y] [-d <dir>]
    # -y         Automatic yes to all prompts
    # -d <dir>   Install the SDK to <dir>
    # ======== Extensible SDK only options ============
    # -n         Do not prepare the build system
    # -p         Publish mode (implies -n)
    # ======== Advanced DEBUGGING ONLY OPTIONS ========
    # -S         Save relocation scripts
    # -R         Do not relocate executables
    # -D         use set -x to see what is going on
    # -l         list files that will be extracted
    "${WORKDIR}/${SRCFILENAME}" "$@"
}

# NOTE: We specify 'dirs' here to handle shells that can't determine path to oe-init.
yocto_sdk_env(){
    cd "${YOCTO_SDK_DEST}"
    . environment-setup-aarch64-poky-linux
}

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    yocto_sdk_installer -l
    yocto_sdk_installer -d "${YOCTO_SDK_DEST}"
}