SUMMARY = "Trivial usage of bb.Fetch2 do_fetch for http"
PV = "23.0.0"
PR = "r0"

inherit bb_fetch2

SRC_URI = "http://downloads.yoctoproject.org/releases/yocto/yocto-3.1/oecore-dunfell-23.0.0.tar.bz2"
SRC_URI[sha256sum] = "108cb7d459e842d2406f90c9882447051d9d67e8fef6d8415d6b6682109e5a95"

do_build(){
    if [ ! -f "${WORKDIR}/oecore-dunfell-23.0.0/oe-init-build-env" ]; then
        echo "FATAL:" 'do_unpack failed'
        exit 1
    fi
}
