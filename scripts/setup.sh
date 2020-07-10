#!/bin/bash
#
# script/setup is used to set up a project in an initial state. This is
# typically run after an initial clone, or, to reset the project back to its
# initial state.
#
# This is also useful for ensuring that your bootstrapping actually works well.

setup_bitbake(){
    local bbdir='bitbake'
    [ -d "${bbdir}/bin/" ] && return
    git submodule update --init bitbake
}

setup_builddir(){
    local bdir='build'
    [ -d "${bdir}" ] && git clean -Xdff "${bdir}"
    mkdir -p \
        "${bdir}/conf/"
    cp -T \
        'conf/bblayers.conf' \
        "${bdir}/conf/bblayers.conf"
    mkdir -p \
        "${bdir}/layers/"
    ln -fsr -t "${bdir}/layers/" \
        layers/* \
        meta-*
}

main(){
    cd "${PROJDIR?}"
    setup_bitbake
    setup_builddir
}

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    # Bash Strict Mode
    set -eu -o pipefail

    SCRIPTDIR="$(CDPATH='' cd -- "$(dirname -- "$0")" && pwd -P)"

    PROJDIR="$(readlink -f "${SCRIPTDIR}"/../)"
    readonly PROJDIR

    set -x
    main "$@"
fi
