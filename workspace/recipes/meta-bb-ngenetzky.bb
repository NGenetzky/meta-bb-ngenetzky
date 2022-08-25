# Copyright (C) 2021 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Demonstration using bitbake directly without open embedded"
HOMEPAGE = "https://github.com/NGenetzky/meta-bb-ngenetzky"
LICENSE = "MIT"

PV = "2021-09-02"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRCREV = "e6681591d8a830d9e4acf0324d6eb3078f82b17a"
SRC_URI = "git://github.com/ngenetzky/meta-bb-ngenetzky.git;destsuffix=${PN}-${PV}/;protocol=https"

do_build[dirs] = "${B}"
do_build(){
    ./scripts/setup.sh
}

addtask ci after do_build
do_ci[dirs] = "${B}"
do_ci(){
    ./ci/r0-simple.sh
    ./ci/r1-simple.sh
    ./ci/r1-test-minimal.sh
    ./ci/r2-test-bb-fetch2.sh
}
