# Copyright (C) 2018 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Parse and manage posts with YAML (or other) frontmatter"

PV = "0.4.3"
PR = "r1"

inherit bb_fetcher
addtask do_unpack before do_build

SRCREV = "749e5c488bc9469e3641e3694f164d9aab7de962"
SRC_URI = "git://github.com/NGenetzky/python-frontmatter.git;protocol=https"
S = "${WORKDIR}/git"

inherit bb_venv
addtask do_bootstrap after do_unpack before do_build

do_bootstrap_append(){
    pip install -r "${S}/requirements.txt"
}

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
