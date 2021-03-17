require yocto-xilinx.bb

bitbake_conf_auto_generate_append(){
    echo 'MACHINE = "zynq-qemu"'
}
