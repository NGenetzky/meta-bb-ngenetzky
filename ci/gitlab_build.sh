#!/bin/bash -xe
source ci/gitlab_console_bitbake.sh

bitbake --version

DEPLOY_DIR="${PWD}/tmp/deploy/"
mkdir -p "${DEPLOY_DIR}"
bitbake -e > "${DEPLOY_DIR}/bitbake.bbenv.bb"

bitbake-layers show-layers
bitbake-layers show-recipes

bitbake example-base-only
bitbake example-bitbake-fetch
bitbake example-bitbake-build-shell

bitbake bitbake-oe
bitbake bitbake-oe-python
(
    source './tmp/work/bitbake-oe-python-2.4-r2/console.sh'
    bitbake -e python > "${DEPLOY_DIR}/python.bbenv.bb"
    bitbake-layers show-layers
    bitbake-layers show-recipes
)

# bitbake-layers layerindex-fetch meta-oe
