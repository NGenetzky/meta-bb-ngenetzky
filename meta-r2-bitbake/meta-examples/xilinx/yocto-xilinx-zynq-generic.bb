require yocto-xilinx.bb
PR_append = ".0"

bitbake_conf_auto_generate_append(){
    echo 'MACHINE = "zynq-generic"'
    echo 'INHERIT += "rm_work"'
    printf 'DL_DIR = "%s"\n' "${DL_DIR}"
}
