
BITBAKE_OE_NAME = "poky"
BITBAKE_OE_VERSION = "2.4"
BITBAKE_OE_ROOT = "${WORKDIR}/${BITBAKE_OE_NAME}"

YOCTO_CACHE_DIR ?= "${PERSISTENT_DIR}/yocto-${BITBAKE_OE_VERSION}/"

BBLAYERS_DIR_SUFFIX ?= "layers/"
BBLAYERS_DIR ?= "${WORKDIR}/${BBLAYERS_DIR_SUFFIX}"

# S = "${BITBAKE_OE_ROOT}"
B = "${WORKDIR}/build"

console(){
    . "${BITBAKE_OE_ROOT}/oe-init-build-env" \
        "${B}" \
        "${BITBAKE_OE_ROOT}/bitbake"
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
}
