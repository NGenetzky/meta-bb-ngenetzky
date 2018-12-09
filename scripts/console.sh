#!/bin/bash
#
# script/console is used to open a console for your application.
#
# A good pattern to support is having an optional argument that is an
# environment name, so you can connect to that environment's console.
#
# You should configure and run anything that needs to happen to open a console
# for the requested environment.

SOURCEDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"

readonly BASEDIR="$(readlink -f ${SOURCEDIR}/../)"
readonly BUILD_DIR="${BASEDIR}/build"
readonly BITBAKE_DIR="${BASEDIR}/bitbake"

bitbake_set_path(){
    local p="$(readlink -f ${1?})"
    [[ -d ${p} ]] || return 1
    export PATH="${p}/bin:$PATH"
    export PYTHONPATH="${p}/lib:$PYTHONPATH"
}

enter_builddir(){
    local p="$(readlink -f ${1?})"
    mkdir -p "${p}"
    export BBPATH="${p}"
    cd "${p}"
}

main(){
    bitbake_set_path "${BITBAKE_DIR}"
    enter_builddir "${BUILD_DIR}"
}

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    echo "Please source $0 rather than execute it."
else
    main

    unset main
    unset bitbake_set_path
    unset cd_to_builddir
fi
