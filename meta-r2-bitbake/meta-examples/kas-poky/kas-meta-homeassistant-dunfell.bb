require kas-local-file.inc

SUMMARY = "Contains recipes to run https://home-assistant.io/ on a OE target."
HOMEPAGE = "https://gitlab.com/bachp/meta-homeassistant"
LICENSE = "MIT"
DEPENDS = ""

SRCREV = "cce36596e1cdcec652ed29b1a1dfdb184b131b82"
SRC_URI = "file://${PN}.yml"

PV = "2020-07-25"
PR = "r1"

inherit bitbake_conf
SRC_URI += "file://site.conf"
YOCTO_CACHE_DIR ?= "${TOPDIR}/var/cache/yocto-poky-3.1.1"
BITBAKE_CONF_FILES = "site.conf"
BITBAKE_CONF_VARS = "YOCTO_CACHE_DIR"

# NOTE: I have a prepopulated cache in different location
YOCTO_CACHE_DIR = "/data/public/ngenetzky/annex/yocto/yocto-3.1.1/build"
