#!/bin/bash
# shellcheck disable=SC1091
source ci/gitlab_console_bitbake.sh

# NOTE: this *must* be done after sourcing 'oe-init-build-env'
# Posix Strict Mode
set -eu

mkdir -p conf/

# NOTE: All variables are escaped so that they are expanded by bitbake.
cat << EOF > 'conf/bblayers.conf'
BBPATH := ""
BBFILES ?= ""
BBLAYERS_DIR = "\${TOPDIR}/../"
BBLAYERS = " \\
 \${BBLAYERS_DIR}/meta-r0-minimal \\
 \${BBLAYERS_DIR}/meta-r1-bb-fetch2 \\
 \${BBLAYERS_DIR}/meta-r1-bb \\
 \${BBLAYERS_DIR}/meta-r2-bitbake \\
 \${BBLAYERS_DIR}/meta-r2-bitbake/meta-examples/ \\
 "
EOF

bitbake --version

mkdir -p './tmp/deploy/'
bitbake -e > ./tmp/deploy/bitbake.bbenv.bb

bitbake-layers show-layers
bitbake-layers show-recipes

bitbake kas-meta-homeassistant-dunfell
