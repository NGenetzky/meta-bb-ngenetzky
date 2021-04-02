
SUMMARY = "Yocto Project Application Development and the Extensible Software Development Kit (eSDK)"
HOMEPAGE = "https://www.yoctoproject.org/docs/${PV}/sdk-manual/sdk-manual.html"

inherit bb_fetcher
addtask do_unpack before do_build

YOCTO_SDK_DEST ??= "${WORKDIR}/poky_sdk/"

yocto_sdk_installer(){
    # Usage: ./poky-glibc-x86_64.sh [-y] [-d <dir>]
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
    chmod u+x "${WORKDIR}/${SRCFILENAME}"
    "${WORKDIR}/${SRCFILENAME}" "$@"
}

yocto_sdk_env(){
    # NOTE: We cd here to handle shells that can't determine path to oe-init.
    cd "${YOCTO_SDK_DEST}"
    . environment-setup-*
}

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    yocto_sdk_installer -l
    yocto_sdk_installer -d "${YOCTO_SDK_DEST}"
}
