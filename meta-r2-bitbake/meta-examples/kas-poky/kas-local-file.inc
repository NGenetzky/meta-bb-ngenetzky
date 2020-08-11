inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "file://${PN}.yml"

inherit bitbake_kas
B = "${WORKDIR}/build/"
KAS_FILES = "${WORKDIR}/${PN}.yml"

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
    export_func_shell('kas_shell', d, os.path.join(workdir, 'kas_shell.sh'), workdir)
    export_func_shell('kas_build', d, os.path.join(workdir, 'kas_build.sh'), workdir)
}

inherit bitbake_cache
BITBAKE_CACHE_KEY_PULL = "${PN}-${PV}"

addtask do_bitbake_conf_template after do_unpack before do_build
do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    # We must use 'kas' at least once, to ensure it is properly fetched.
    kas_exec shell "${KAS_FILES}" -c 'bitbake-layers show-recipes > recipes.log'
}

console(){
    kas_shell
}
