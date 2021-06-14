
PV = "2.9.7"
PR = "r0"
S = "${WORKDIR}"

inherit bb_venv
addtask do_bootstrap before do_build
BB_VENV_PIP3 = "\
ansible==2.9.7 \
ansible-lint==4.2.0 \
"

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('activate', d, os.path.join(workdir, 'activate.sh'), workdir)
}

do_build[dirs] = "${S}"
do_build(){
    activate
    python3 -m pip install ${BB_VENV_PIP3}
}
