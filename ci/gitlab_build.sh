#!/bin/bash -xe
source ci/gitlab_console_bitbake.sh
bitbake --version
bitbake-layers show-recipes
bitbake bitbake