PV = "2020.1"
PR = "r1"
inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "repo://github.com/Xilinx/yocto-manifests.git;protocol=git;branch=rel-v2020.1"
S = "${WORKDIR}/repo/"

inherit bitbake_kas
KAS_FILES = "${S}/kas-xilinx-rel-v2020.1.yml"
SRC_URI =+ "file://kas-xilinx-rel-v2020.1.yml"

inherit bitbake_oe
BITBAKE_OE_FILE = "setupsdk"

B = "${WORKDIR}/build/"
do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    # NOTE: This currently fails:
    # oe_init_build_env
    # bitbake-layers show-recipes > recipes.log

    cp "${WORKDIR}/kas-xilinx-rel-v2020.1.yml" \
        "${S}/kas-xilinx-rel-v2020.1.yml"
    kas_build
}

