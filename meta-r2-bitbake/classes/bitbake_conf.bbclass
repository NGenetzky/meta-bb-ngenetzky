
BITBAKE_CONF_TEMPLATE ??= "${WORKDIR}"
BITBAKE_CONF_BUILD ??= "${B}/conf"
BITBAKE_CONF_FILES ??= "bblayers.conf local.conf site.conf"
BITBAKE_CONF_VARS ??= "WORKDIR B"

do_bitbake_conf_template[nostamp] = "1"
do_bitbake_conf_template[dirs] = "${BITBAKE_CONF_BUILD} ${BITBAKE_CONF_TEMPLATE}"
addtask do_bitbake_conf_template before do_build
python do_bitbake_conf_template(){
    templateconf = d.getVar('BITBAKE_CONF_TEMPLATE')
    buildconf = d.getVar('BITBAKE_CONF_BUILD')
    files = d.getVar('BITBAKE_CONF_FILES').split()

    bbvars = {}
    for var in d.getVar('BITBAKE_CONF_VARS').split():
        bbvars[var] = d.getVar(var)
        if bbvars[var] is None:
            bb.fatal('{0} is in BITBAKE_CONF_VARS but is not defined'.format(var))
    bb.debug(1, 'bbvars={0}'.format(bbvars))

    for c_file in files:
        i_file = os.path.join(templateconf, c_file)
        if not os.path.exists(i_file):
            continue
        o_file = os.path.join(buildconf, c_file)

        contents = ""
        with open(i_file, 'r') as conf:
            contents = conf.read()

        for var in bbvars:
            contents = contents.replace('{{ '+var+' }}', bbvars[var])

        bb.debug(1, '{0}->{1}'.format(o_file,i_file))
        with open(o_file, 'w') as conf:
            conf.write(contents)
}

