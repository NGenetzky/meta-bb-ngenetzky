# TODO: Warn if "${B}" != "${WORKDIR}/build/"

KAS_FILES = "${WORKDIR}/kas.yml"

exec_kas(){
    # WARNING: This assumes you are inside of docker container
    # TODO: Make it more flexbile
    python3 -m kas "$@"
}

console(){
    KAS_REPO_REF_DIR="${DL_DIR}/git/"
    export KAS_REPO_REF_DIR

    exec_kas shell "${KAS_FILES}"
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
    KAS_REPO_REF_DIR="${DL_DIR}/git/"
    export KAS_REPO_REF_DIR

    # We must use 'kas' at least once, to ensure it is properly fetched.
    exec_kas shell "${KAS_FILES}" -c 'bitbake-layers show-recipes > recipes.log'
}
