SUMMARY = "Balena layer for Intel x86_64"
HOMEPAGE = "https://github.com/balena-os/balena-intel"
LICENSE = "APACHE-2.0"

PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "gitsm://github.com/balena-os/balena-intel.git;destsuffix=${PN}-${PV}/"
SRCREV = "242c3f7388334c3d3174a8fe62b74693eeb5c55b"

inherit bitbake_cache

barys_exec(){
    "${S}/balena-yocto-scripts/build/barys"
}

do_build[dirs] = "${B} ${WORKDIR}"
do_build(){
    "${S}/balena-yocto-scripts/build/barys" --dry-run
}
