SUMMARY = "The official balena CLI tool"
PV = "12.9.7"
PR = "r1"

inherit bb_venv
addtask do_bootstrap before do_build

do_bootstrap_append(){
    if [ -z "${VIRTUAL_ENV+x}" ] ; then
        bbexit "Failed to setup virtual env"
        exit 1
    fi
    if [ -z "${NODE_VIRTUAL_ENV+x}" ] ; then
        pip install nodeenv
        nodeenv -p
        bbnote "Installed nodeenv V=$(nodeenv --version)"

    fi
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('activate', d, os.path.join(workdir, 'activate.sh'), workdir)
}

do_build[dirs] = "${B}"
do_build(){
    activate
    # TODO: Build from source
    npm install -g --production \
        "balena-cli@${PV}"
}
