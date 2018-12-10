
BITBAKE_OE_NAME = "oe"
BITBAKE_OE_VERSION = "2.4"
BITBAKE_OE_ROOT = "${WORKDIR}/${BITBAKE_OE_NAME}"

inherit bb_fetcher
addtask do_unpack before do_build
# openembedded-core_2.4
SRC_URI += "git://github.com/openembedded/openembedded-core.git;destsuffix=${BITBAKE_OE_NAME};branch=rocko;rev=7d518d342eb67d25aa071fb08d03f06d6da576c6"
# bitbake_1.39
SRC_URI += "git://github.com/openembedded/bitbake.git;destsuffix=${BITBAKE_OE_NAME}/bitbake;rev=82ea737a0b42a8b53e11c9cde141e9e9c0bd8c40"
# meta-openembedded_2.4
SRC_URI += "git://github.com/openembedded/meta-openembedded.git;destsuffix=${BITBAKE_OE_NAME}/meta-openembedded;branch=rocko;rev=eae996301d9c097bcbeb8046f08041dc82bb62f8"

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
