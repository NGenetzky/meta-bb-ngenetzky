inherit bb_fetcher
addtask do_unpack before do_build

# febbe2944c (HEAD, tag: yocto-3.1.1, tag: dunfell-23.0.1)
SRCREV = "febbe2944c0c4a04b85fa98fdc261186115954d8"
SRC_URI = "\
git://git.yoctoproject.org/poky.git;name=poky_warrior;branch=warrior;destsuffix=${PN}-${PV}/ \
git://github.com/meta-debian/meta-debian.git;name=debian_warrior;branch=warrior;destsuffix=meta-debian/ \
"
SRCREV_poky_warrior = "f65b24e9ca0918a4ede70ea48ed8b7cc4620f07f"
# SRCREV_debian_warrior = "a02e7a8bc7dccdf08fa8f3d2a4b9705a271a1215" # 2021.04.14
SRCREV_debian_warrior = "a02e7a8bc7dccdf08fa8f3d2a4b9705a271a1215"
SRC_URI += "file://site.conf"

inherit bitbake_oe
BITBAKE_OE_ROOT = "${WORKDIR}/${PN}-${PV}/"

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    oe_init_build_env
    bitbake-layers show-recipes > recipes.log
}
