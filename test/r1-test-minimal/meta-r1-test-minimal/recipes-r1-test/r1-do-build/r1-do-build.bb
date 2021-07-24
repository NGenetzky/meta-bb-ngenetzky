addtask build
do_build[dirs] = "${TOPDIR}"
do_build[nostamp] = "1"
python do_build () {
    bb.note("The included, default BB base.bbclass does not define a useful default task.")
    bb.note("Try running the 'listtasks' task against a .bb to see what tasks are defined.")
}
