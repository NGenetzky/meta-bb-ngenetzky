#!/bin/sh

bitbake_set_path(){
    local p="$(readlink -f ${1?})"
    [ -d ${p} ] || return 1
    export PATH="${p}/bin:$PATH"
    export PYTHONPATH="${p}/lib:$PYTHONPATH"
}

enter_builddir(){
    local p="$(readlink -f ${1?})"
    mkdir -p "${p}"
    export BBPATH="${p}"
    cd 'build'
}

export LANG=en_US.UTF-8
bitbake_set_path 'bitbake/'
enter_builddir 'build/'

unset bitbake_set_path
unset enter_builddir
