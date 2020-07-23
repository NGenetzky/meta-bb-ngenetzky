SUMMARY = "Build using OpenEmbedded"
PV = "2.4"
PR = "r1"

inherit bitbake_oe

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = ""
# openembedded-core_2.4
SRC_URI += "git://github.com/openembedded/openembedded-core.git;destsuffix=${BBLAYERS_DIR};branch=rocko;rev=7d518d342eb67d25aa071fb08d03f06d6da576c6"
# bitbake_1.39
SRC_URI += "git://github.com/openembedded/bitbake.git;destsuffix=${BBLAYERS_DIR}/bitbake;rev=82ea737a0b42a8b53e11c9cde141e9e9c0bd8c40"
# meta-openembedded_2.4
SRC_URI += "git://github.com/openembedded/meta-openembedded.git;destsuffix=${BBLAYERS_DIR}/meta-openembedded;branch=rocko;rev=eae996301d9c097bcbeb8046f08041dc82bb62f8"

do_build_append(){
    bitbake-layers show-layers > "${WORKDIR}/${PF}.layers.log"
    bitbake-layers show-recipes > "${WORKDIR}/${PF}.recipes.log"
}

