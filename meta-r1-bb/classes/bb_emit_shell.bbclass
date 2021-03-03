def bb_emit_shell_header():
    return '''\
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
    if cwd is None:
        cwd = d.getVarFlag(func, 'dirs')

    shtype = d.getVarFlag(func, 'shtask')
    if shtype is None:
        shtype = 'shtask'

    if shtype in ['shtask', 'shcli']:
        func_header = ['#!/bin/sh']
    elif shtype in ['bashtask', 'bashcli']:
        func_header = ['#!/bin/bash']
    func_header.append(bb_emit_shell_header())

    func_footer = []
    if bb.msg.loggerVerboseLogs:
        func_footer.append("set -x\n")
    if cwd:
        func_footer.append("cd '{}'\n".format(cwd))

    if shtype in ['shtask', 'bashtask']:
        func_footer.append('{}'.format(func))
    elif shtype in ['shcli', 'bashcli']:
        func_footer.append('{} $@'.format(func))
    func_footer.extend([ '#cleanup', 'ret=$?', "trap '' 0", 'exit $ret', '' ])

    with open(runfile, 'w') as script:
        script.write('\n'.join(func_header))
        bb.data.emit_func(func, script, d)
        script.write('\n'.join(func_footer))

    os.chmod(runfile, 0o775)

do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts
python do_build_shell_scripts(){
}
