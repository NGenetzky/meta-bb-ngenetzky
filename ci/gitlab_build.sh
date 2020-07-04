#!/bin/bash
source ci/gitlab_console_bitbake.sh

# NOTE: this *must* be done after sourcing 'oe-init-build-env'
# Posix Strict Mode
set -eu

bitbake --version

mkdir -p './tmp/deploy/'
bitbake -e > ./tmp/deploy/bitbake.bbenv.bb

bitbake-layers show-layers
bitbake-layers show-recipes

bitbake example-base-only
bitbake example-bitbake-fetch
bitbake example-bitbake-build-shell

bitbake-layers layerindex-fetch meta-oe
