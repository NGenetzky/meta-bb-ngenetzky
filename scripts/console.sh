#!/bin/bash
#
# script/console is used to open a console for your application.
#
# A good pattern to support is having an optional argument that is an
# environment name, so you can connect to that environment's console.
#
# You should configure and run anything that needs to happen to open a console
# for the requested environment.

oe_init_build_env(){
    # We are attempting to other shells besides bash
    # shellcheck disable=SC2128
    if [ -n "$BASH_SOURCE" ]; then
        OEROOT="$(dirname "$(readlink -f "$BASH_SOURCE")")/../"
    else
        OEROOT="./"
    fi
    _DEFAULT_OE_INIT="${OEROOT}/bb-init-build-env"

    OE_INIT_BUILD_ENV="${OE_INIT_BUILD_ENV-"${_DEFAULT_OE_INIT}"}"
    if [ ! -e "$OE_INIT_BUILD_ENV" ]; then
        echo "Error: $OE_INIT_BUILD_ENV doesn't exist!" >&2
        echo "Please source this script in BASEDIR." >&2
        return 1
    fi

    # shellcheck disable=SC1090
    . "${OE_INIT_BUILD_ENV}"
}

oe_init_build_env "$@"
unset oe_init_build_env
