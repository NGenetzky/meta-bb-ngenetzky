SUMMARY = "Build meta-python with OpenEmbedded"
PV = "2.4"
PR = "r1"

inherit bitbake_oe

SRC_URI += "\
    file://bblayers.conf \
    file://local.conf \
    file://site.conf \
"
