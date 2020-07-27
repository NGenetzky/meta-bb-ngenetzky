# TODO: Warn if "${B}" != "${WORKDIR}/build/"

KAS_FILES = "${WORKDIR}/kas.yml"
KAS_REPO_REF_DIR ??= "${DL_DIR}/git/"

# ENV for kas
export KAS_REPO_REF_DIR

kas_exec(){
    # WARNING: This assumes you are inside of docker container
    # TODO: Make it more flexbile
    python3 -m kas "$@"
}

kas_shell(){
    kas_exec shell "${KAS_FILES}"
}

kas_build(){
    kas_exec build "${KAS_FILES}"
}

console(){
    kas_shell
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('console', d, os.path.join(workdir, 'console.sh'), workdir)
    export_func_shell('kas_shell', d, os.path.join(workdir, 'kas_shell.sh'), workdir)
    export_func_shell('kas_build', d, os.path.join(workdir, 'kas_build.sh'), workdir)
}

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    # We must use 'kas' at least once, to ensure it is properly fetched.
    kas_exec shell "${KAS_FILES}" -c 'bitbake-layers show-recipes > recipes.log'
}
