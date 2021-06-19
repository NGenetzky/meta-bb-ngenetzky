PV = "2020.1"
PR = "r3"
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

inherit bitbake_cache

BITBAKE_CACHE_KEY = "yocto"

inherit bitbake_conf
addtask do_bitbake_conf_auto_generate before do_build
SRC_URI += "file://site.conf"
YOCTO_CACHE_DIR ?= "${TOPDIR}/var/cache/${BITBAKE_CACHE_KEY}/"
BITBAKE_CONF_FILES = "site.conf"
BITBAKE_CONF_VARS = "YOCTO_CACHE_DIR"

bitbake_conf_auto(){
    # NOTE: The petalinux TEMPLATECONF has invalid MACHINE in v2020.1
    echo 'MACHINE ?= "qemu-zynq7"'
    echo 'INHERIT += "rm_work"'
    printf 'DL_DIR = "%s"\n' "${YOCTO_DOWNLOADS}"
    printf 'SSTATE_DIR = "%s"\n' "${YOCTO_STATE_CACHE}"
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
    export_func_shell('do_bitbake_build', d, os.path.join(workdir, 'do_bitbake_build.sh'), workdir)
}

B = "${WORKDIR}/build/"
do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    oe_init_build_env
    bitbake-layers show-recipes > recipes.log
}

console(){
    oe_init_build_env
    # Don't exit when failure occurs.
    set +e
}

addtask do_bitbake_build
do_bitbake_build(){
    oe_init_build_env
    bitbake "petalinux-image-full" "$@"
}
