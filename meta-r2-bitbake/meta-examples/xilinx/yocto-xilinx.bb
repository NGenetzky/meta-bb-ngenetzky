inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "repo://github.com/Xilinx/yocto-manifests.git;protocol=git;branch=rel-v2020.1"
S = "${WORKDIR}/repo/"

inherit bitbake_oe
BITBAKE_OE_FILE = "setupsdk"

B = "${WORKDIR}/build/"
do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    oe_init_build_env
    bitbake-layers show-recipes > recipes.log
}
