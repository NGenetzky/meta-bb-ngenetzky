require kas-local-file.inc

LICENSE = "MIT"
DEPENDS = ""

SRCREV = "cce36596e1cdcec652ed29b1a1dfdb184b131b82"
SRC_URI = "file://${PN}-${PV}.yml"

PV = "2.6.4"
PR = "r1"

inherit bitbake_conf
SRC_URI += "file://site.conf"
YOCTO_CACHE_DIR ?= "${TOPDIR}/var/cache/yocto/"
BITBAKE_CONF_FILES = "site.conf"
BITBAKE_CONF_VARS = "YOCTO_CACHE_DIR"
