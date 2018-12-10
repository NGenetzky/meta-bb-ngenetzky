#!/bin/bash -xe
source ci/gitlab_console_bitbake.sh

bitbake --version

mkdir -p './tmp/deploy/'
bitbake -e > ./tmp/deploy/bitbake.bbenv.bb

bitbake-layers show-layers
bitbake-layers show-recipes

bitbake example-base-only
bitbake example-bitbake-fetch
bitbake example-bitbake-build-shell

bitbake bitbake-oe

# bitbake-layers layerindex-fetch meta-oe
