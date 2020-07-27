SUMMARY = "Collection of kas configuration files"
HOMEPAGE = "https://github.com/texierp/kas-files"

PV = "2020-07-23"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRCREV = "65b03fc80dffeee7fb1718420b20de5cef77579d"
SRC_URI = "git://github.com/texierp/kas-files.git;destsuffix=${PN}-${PV}/"

inherit bitbake_kas
B = "${WORKDIR}/build/"
KAS_FILES = "${S}/kas-poky.yml"

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
BITBAKE_CACHE_KEY_PULL = "texiep-kas-poky-minimal-${PV}-${PR}"

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    # We must use 'kas' at least once, to ensure it is properly fetched.
    kas_exec shell "${KAS_FILES}" -c 'bitbake-layers show-recipes > recipes.log'
}

console(){
    kas_shell
}
