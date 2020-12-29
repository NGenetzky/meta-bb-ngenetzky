SUMMARY = "Trivial usage of bb_build_shell"
PV = "1.38"
PR = "r0"
LICENSE = ""

inherit bb_fetcher
addtask do_unpack before do_build
SRCREV = "82ea737a0b42a8b53e11c9cde141e9e9c0bd8c40"
SRC_URI = "git://github.com/openembedded/bitbake.git"
S = "${WORKDIR}/git"

bitbake_set_path(){
    local p="$(readlink -f ${1?})"
    [[ -d ${p} ]] || return 1
    export PATH="${p}/bin:$PATH"
    export PYTHONPATH="${p}/lib:$PYTHONPATH"
}

console(){
    bitbake_set_path "${S}"
    export BBPATH="${TOPDIR}"
    cd "${TOPDIR}"
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
}

do_build(){
    [ -f ${WORKDIR}/console.sh ] || exit 1
}
