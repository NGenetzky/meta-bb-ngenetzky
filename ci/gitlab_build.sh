#!/bin/bash -xe
source ci/gitlab_console_bitbake.sh

bitbake --version
bitbake -e > ./tmp/deploy/bitbake.bbenv.bb

bitbake-layers show-layers
bitbake-layers show-recipes

bitbake example-base-only

bitbake-layers layerindex-fetch meta-oe