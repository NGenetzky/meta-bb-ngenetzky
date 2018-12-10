SUMMARY = "Trivial usage of bb_fetch"
PV = "1.38"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build

SRCREV = "82ea737a0b42a8b53e11c9cde141e9e9c0bd8c40"
SRC_URI = "git://github.com/openembedded/bitbake.git"

S = "${WORKDIR}/git"

do_build(){
    [ -f ${S}/bin/bitbake ] || exit 1
}
