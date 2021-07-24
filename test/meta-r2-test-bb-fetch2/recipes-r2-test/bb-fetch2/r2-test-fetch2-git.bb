SUMMARY = "Trivial usage of bb.Fetch2 do_fetch"
PV = "1.46.0"
PR = "r0"

inherit bb_fetch2

SRCREV = "4618da2094189e4d814b7d65672cb65c86c0626a"
SRC_URI = "git://github.com/openembedded/bitbake.git"

S = "${WORKDIR}/git"

do_build(){
    [ -f ${S}/bin/bitbake ] || exit 1
}
