#!/bin/bash
#
# script/setup is used to set up a project in an initial state. This is
# typically run after an initial clone, or, to reset the project back to its
# initial state.
#
# This is also useful for ensuring that your bootstrapping actually works well.

setup_bitbake(){
    # local _BITBAKEDIR
    _BITBAKEDIR='bitbake'
    [ -d "${_BITBAKEDIR}/bin/" ] && return
    git submodule update --init "${_BITBAKEDIR}"
}

setup_builddir(){
    # local _BUILDDIR _BBLAYERS_FETCH_DIR
    _BUILDDIR="build"
    _BBLAYERS_FETCH_DIR="build/layers"
    [ -d "${_BUILDDIR}" ] && git clean -Xdff "${_BUILDDIR}"
    mkdir -p \
        "${_BUILDDIR}/conf/" \
        "${_BBLAYERS_FETCH_DIR}"
    cp -T \
        'conf/bblayers.conf' \
        "${_BUILDDIR}/conf/bblayers.conf"

    ln -fsr -t "${_BBLAYERS_FETCH_DIR}" \
        "./../${PROJECT_NAME}"
    # primary layers
    ln -fsr -t "${_BBLAYERS_FETCH_DIR}" \
        meta-*
}

main(){
    if [ -n "${PROJECT_ROOT-}" ]; then
        PROJECT_ROOT="$(readlink -f "${PROJECT_ROOT}")"
    else
        PROJECT_ROOT="$(pwd)"
    fi

    if [ -z "${PROJECT_NAME-}" ]; then
        PROJECT_NAME="$(basename ${PROJECT_ROOT})"
    fi

    # cd to PROJECT_ROOT for consistent context
    cd "${PROJECT_ROOT?}"

    setup_bitbake
    setup_builddir
}

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    # Bash Strict Mode
    set -eu -o pipefail

    SCRIPTDIR="$(CDPATH='' cd -- "$(dirname -- "$0")" && pwd -P)"

    # cd to PROJECT_ROOT for consistent context
    cd "$SCRIPTDIR/../"

    set -x
    main "$@"
fi
