require yocto-xilinx.bb
PR_append = ".1"

bitbake_conf_auto_append(){
    echo 'MACHINE = "zynq-generic"'
}
