def shell_trap_code():
    return '''#!/bin/sh\n
 # Emit a useful diagnostic if something fails:
 bb_exit_handler() {
     ret=$?
     case $ret in
     0)  ;;
     *)  case $BASH_VERSION in
         "") echo "WARNING: exit code $ret from a shell command.";;
         *)  echo "WARNING: ${BASH_SOURCE[0]}:${BASH_LINENO[0]} exit $ret from '$BASH_COMMAND'";;
         esac
         exit $ret
     esac
 }
 trap 'bb_exit_handler' 0
 set -e
 '''

def shell_trap_wrapper(func_name):
    return '\n'.join([
        'bb_run_handler() {',
        '    {} "$@"'.format(func_name),
        "    ret=$?",
        "    trap '' 0",
        "    return $ret",
        "}",
        'bb_run_handler "$@"'
    ])

def export_func_shell(func, d, runfile, cwd=None):
    """Execute a shell function from the metadata

    Note on directory behavior.  The 'dirs' varflag should contain a list
    of the directories you need created prior to execution.  The last
    item in the list is where we will chdir/cd to.

    References:
        bitbake.bb.build.exec_func_shell()
    """
    if d.getVar(func) is None:
        raise bb.fatal('Function ({0}) not defined'.format(func))
    if cwd is None:
        cwd = d.getVarFlag(func, 'dirs')

    with open(runfile, 'w') as script:
        script.write(shell_trap_code())

        bb.data.emit_func(func, script, d)

        if bb.msg.loggerVerboseLogs:
            script.write("set -x\n")
        if cwd:
            script.write("cd '%s'\n" % cwd)

        script.write(shell_trap_wrapper(func))

    os.chmod(runfile, 0o775)

do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts
python do_build_shell_scripts(){
}
