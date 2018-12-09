#!/bin/bash
#
# script/cibuild: Setup environment for CI to run tests. This is primarily
#                 designed to run on the continuous integration server.

SCRIPTDIR="$(CDPATH='' cd -- "$(dirname -- "$0")" && pwd -P)"

readonly PROJDIR="$(readlink -f ${SCRIPTDIR}/../)"

clone_and_enter_project(){
    local workspace="$(mktemp -d)"
    trap "rm -rf ${workspace}" EXIT
    git clone \
        "${PROJDIR}" \
        "${workspace}"
    cd "${workspace}"
}

main(){
    clone_and_enter_project
    docker build -t 'cibuild-meta-bb-ngenetzky' .
}

if [[ "${BASH_SOURCE[0]}" == "${0}" ]]; then
    # Bash Strict Mode
    set -eu -o pipefail

    set -x
    main "$@"
fi
