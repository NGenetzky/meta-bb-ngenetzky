# Copyright (C) 2018 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Setup tool for bitbake based projects"

PV = "2.1.1"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build

# https://github.com/siemens/kas/releases/tag/2.1.1
SRCREV = "d02e87a0a70464803004d35792fafdd3b3b77f39"
SRC_URI = "git://github.com/siemens/kas.git"
S = "${WORKDIR}/git"

inherit bb_venv
addtask do_bootstrap after do_unpack before do_build

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
    python3 ${S}/setup.py build
    python3 ${S}/setup.py install
}
