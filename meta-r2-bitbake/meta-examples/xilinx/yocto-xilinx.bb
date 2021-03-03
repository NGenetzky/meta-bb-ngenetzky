PV = "2020.1"
PR = "r1"
inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "repo://github.com/Xilinx/yocto-manifests.git;protocol=git;branch=rel-v2020.1"
S = "${WORKDIR}/repo/"

inherit bitbake_oe
# BITBAKE_OE_FILE = "setupsdk"
BITBAKE_OE_ROOT = "${S}/sources/core/"

oe_init_build_env_prepend(){
    export TEMPLATECONF="${S}/sources/meta-petalinux/conf"
    export PATH="$PATH:${S}/scripts"
}

bitbake_conf_auto_generate(){
    # NOTE: The petalinux TEMPLATECONF has invalid MACHINE in v2020.1
    echo 'MACHINE ?= "qemu-zynq7"'
}

B = "${WORKDIR}/build/"
do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    mkdir -p "${B}/conf/"
    bitbake_conf_auto_generate > "${B}/conf/auto.conf"

    oe_init_build_env
    bitbake-layers show-recipes > recipes.log
}
