SUMMARY = "The official balena CLI tool"
PV = "12.9.7"
PR = "r0"

B = "${WORKDIR}/env"

activate(){
    . '${B}/bin/activate'
}

addtask do_bootstrap after do_setup before do_build
do_bootstrap(){
    virtualenv "${B}"
    activate
    pip install nodeenv
    nodeenv -p
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('activate', d, os.path.join(workdir, 'activate.sh'), workdir)
}

do_build[dirs] = "${B} ${S}"
do_build(){
    activate
    # TODO: Build from source
    npm install -g --production \
        "balena-cli@${PV}"
}
