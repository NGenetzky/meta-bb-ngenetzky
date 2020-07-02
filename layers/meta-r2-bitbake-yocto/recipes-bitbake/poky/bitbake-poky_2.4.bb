SUMMARY = "Build using Poky"
PV = "2.4"
PR = "r0"

inherit bitbake_oe

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = ""

# poky 2.4
SRC_URI += "git://git.yoctoproject.org/poky.git;destsuffix=${BBLAYERS_DIR_SUFFIX};name=poky"
SRCREV_poky = "05711ba18587aaaf4a9c465a1dd4537f27ceda93"

SRC_URI += "\
    file://bblayers.conf \
    file://local.conf \
    file://site.conf \
"

inherit bitbake_conf
BITBAKE_CONF_VARS = "\
    B \
    BITBAKE_OE_ROOT \
    YOCTO_CACHE_DIR \
"
