#!/bin/bash
#
# script/setup is used to set up a project in an initial state. This is
# typically run after an initial clone, or, to reset the project back to its
# initial state.
#
# This is also useful for ensuring that your bootstrapping actually works well.

# "NOTE: OECOMPAT" marks sections designed for compatibility with OE.
# In an attempt to be more compatible with other projects that use
# bitbake (openembeded, yocto, poky) we support their variable names.

setup_bitbake(){
    # local _BITBAKEDIR
    _BITBAKEDIR='bitbake'
    [ -d "${_BITBAKEDIR}/bin/" ] && return
    git submodule update --init "${_BITBAKEDIR}"
}

setup_build_conf(){
    _BUILDDIR="build"

    # NOTE: OECOMPAT
    #
    # $TEMPLATECONF can point to a directory for the template local.conf & bblayers.conf
    #
    if [ -n "${TEMPLATECONF-}" ]; then
        if [ ! -d "$TEMPLATECONF" ]; then
            # Allow TEMPLATECONF=meta-xyz/conf as a shortcut
            if [ -d "$OEROOT/$TEMPLATECONF" ]; then
                TEMPLATECONF="$OEROOT/$TEMPLATECONF"
            fi
            if [ ! -d "$TEMPLATECONF" ]; then
                echo >&2 "Error: TEMPLATECONF value points to nonexistent directory '$TEMPLATECONF'"
                exit 1
            fi
        fi
    else
        TEMPLATECONF="${PROJECT_ROOT}"
    fi

    # NOTE: OECOMPAT
    if [ -f "$TEMPLATECONF/bblayers.conf.sample" ]; then
        # Put the abosolute path to the layers in bblayers.conf so we can run
        # bitbake without the init script after the first run
        # ##COREBASE## is deprecated as it's meaning was inconsistent, but continue
        # to replace it for compatibility.
        sed -e "s|##OEROOT##|$OEROOT|g" \
            -e "s|##COREBASE##|$OEROOT|g" \
            "$TEMPLATECONF/bblayers.conf.sample" \
            > "${_BUILDDIR}/conf/bblayers.conf"
    fi

    # We would prefer to use relative symlinks for our layers.
    ln -fsr -t "${_BBLAYERS_FETCH_DIR}" \
        "./../${PROJECT_NAME}"
    # primary layers
    ln -fsr -t "${_BBLAYERS_FETCH_DIR}" \
        meta-*
}

setup_builddir(){
    # local _BUILDDIR _BBLAYERS_FETCH_DIR
    _BUILDDIR="build"
    _BBLAYERS_FETCH_DIR="build/layers"
    [ -d "${_BUILDDIR}" ] && git clean -Xdff "${_BUILDDIR}"
    mkdir -p \
        "${_BUILDDIR}/conf/" \
        "${_BBLAYERS_FETCH_DIR}"

    setup_build_conf
}

main(){
    if [ -n "${PROJECT_ROOT-}" ]; then
        PROJECT_ROOT="$(readlink -f "${PROJECT_ROOT}")"
    else
        PROJECT_ROOT="$(pwd)"
    fi

    # cd to PROJECT_ROOT for consistent context
    cd "${PROJECT_ROOT?}"

    if [ -z "${PROJECT_NAME-}" ]; then
        PROJECT_NAME="$(basename ${PROJECT_ROOT})"
    fi

    # NOTE: OECOMPAT
    OEROOT="${PROJECT_ROOT}"

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
