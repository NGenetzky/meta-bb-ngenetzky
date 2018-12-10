SUMMARY = "Build meta-python with OpenEmbedded"
PV = "2.4"
PR = "r2"

inherit bitbake_oe

inherit bitbake_conf
BITBAKE_CONF_VARS = "\
    B \
    BBLAYERS_DIR \
    YOCTO_CACHE_DIR \
"

SRC_URI += "\
    file://bblayers.conf \
    file://local.conf \
    file://site.conf \
"
