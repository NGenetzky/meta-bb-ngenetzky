require kas-local-file.inc

PROVIDES = "meta-debian"

# SUMMARY = "" # TODO
# HOMEPAGE = "" # TODO

# 2.7 # warrior
# 2019-10-16 # 5a88ced3b0f58360e4565e2d15d91217c191b9bc
PV = "2.7.2021.03.10"
PR = "r1"

SRC_URI = "file://${PN}.yml"

inherit bitbake_conf
SRC_URI += "file://site.conf"
YOCTO_CACHE_DIR ?= "${TOPDIR}/var/cache/yocto-poky-2.7"
BITBAKE_CONF_FILES = "site.conf"
BITBAKE_CONF_VARS = "YOCTO_CACHE_DIR"

addtask do_kas_build after do_build
do_kas_build(){
    kas_exec build "${KAS_FILES}"
}
