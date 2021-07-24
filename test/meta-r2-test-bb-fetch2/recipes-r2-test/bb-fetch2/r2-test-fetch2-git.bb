SUMMARY = "Trivial usage of bb.Fetch2 do_fetch"
PV = "1.46.0"
PR = "r0"

inherit bb_fetch2

SRCREV = "4618da2094189e4d814b7d65672cb65c86c0626a"
SRC_URI = "git://github.com/openembedded/bitbake.git"

S = "${WORKDIR}/git"

verify_fetch(){
    # NOTE: This function is imperfect, various variables could be changed.
    if [ -d "${DL_DIR}/git2/github.com.openembedded.bitbake.git" ]; then
        return 0
    elif [ -d "${TOPDIR}/tmp/downloads/git2/github.com.openembedded.bitbake.git" ]; then
        return 0
    fi
    return 1
}

do_build(){
    if ! verify_fetch ; then
        echo "FATAL:" 'do_fetch failed'
        exit 1
    fi

    if [ ! -x "${S}/bin/bitbake" ]; then
        echo "FATAL:" 'do_unpack failed'
        exit 1
    fi
}
