SUMMARY = "Build meta-python with OpenEmbedded"
PV = "2.4"
PR = "r2"

inherit bitbake_oe

inherit bitbake_conf
BITBAKE_CONF_VARS = "\
    B \
    BITBAKE_OE_ROOT \
    YOCTO_CACHE_DIR \
"

SRC_URI += "\
    file://bblayers.conf \
    file://local.conf \
    file://site.conf \
"

do_build_append(){
    bitbake-layers show-layers > "${WORKDIR}/${PF}.layers.log"
    bitbake-layers show-recipes > "${WORKDIR}/${PF}.recipes.log"

    cp -t "${DEPLOY_DIR}" \
        "${WORKDIR}/${PF}.layers.log" \
        "${WORKDIR}/${PF}.recipes.log"
}
