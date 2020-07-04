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
        THIS_SCRIPT=$BASH_SOURCE
    elif [ -n "$ZSH_NAME" ]; then
        THIS_SCRIPT=$0
    else
        THIS_SCRIPT="$(pwd)/oe-init-build-env"
        if [ ! -e "$THIS_SCRIPT" ]; then
            echo "Error: $THIS_SCRIPT doesn't exist!" >&2
            echo "Please run this script in oe-init-build-env's directory." >&2
            return 1
        fi
    fi

    BASEDIR="$(dirname "$(readlink -f "$THIS_SCRIPT")")"
    # shellcheck disable=SC1090
    . "${BASEDIR}/oe-init-build-env"
}

oe_init_build_env "$@"
unset oe_init_build_env
