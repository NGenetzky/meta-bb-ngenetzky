SUMMARY = "Balena layer for Intel x86_64"
HOMEPAGE = "https://github.com/balena-os/balena-intel"
LICENSE = "APACHE-2.0"

PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "gitsm://github.com/balena-os/balena-intel.git;destsuffix=${PN}-${PV}/"
SRCREV = "242c3f7388334c3d3174a8fe62b74693eeb5c55b"

B = "${S}/build/"

inherit bitbake_cache

barys_exec(){
    "${S}/balena-yocto-scripts/build/barys" "$@"
}

BITBAKE_OE_ROOT = "${S}/layers/poky"
console[dirs] = "${BITBAKE_OE_ROOT}"
console(){
    # TODO: Do proper store and resume
    set +e

    . "${BITBAKE_OE_ROOT}/oe-init-build-env" \
        "${B}" \
        "${BITBAKE_OE_ROOT}/bitbake"

    # TODO: Do proper store and resume
    set -e
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
}

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    barys_exec --dry-run
}
