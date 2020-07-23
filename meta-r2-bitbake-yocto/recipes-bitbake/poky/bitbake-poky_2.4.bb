SUMMARY = "Build using Poky"
PV = "2.4"
PR = "r0"

inherit bitbake_poky

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = ""

# poky 2.4
SRC_URI += "git://git.yoctoproject.org/poky.git;destsuffix=${BBLAYERS_DIR_SUFFIX}/poky/;name=poky"
SRCREV_poky = "65d23bd7986615fdfb0f1717b615534a2a14ab80"

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

do_build_append(){
    bitbake-layers show-layers > "${WORKDIR}/${PF}.layers.log"
    bitbake-layers show-recipes > "${WORKDIR}/${PF}.recipes.log"

    cp -t "${DEPLOY_DIR}" \
        "${WORKDIR}/${PF}.layers.log" \
        "${WORKDIR}/${PF}.recipes.log"
}

