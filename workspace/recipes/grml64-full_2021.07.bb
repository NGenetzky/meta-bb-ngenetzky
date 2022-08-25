
# Copyright (C) 2021 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Grml is a bootable live system (Live-CD) based on Debian."
HOMEPAGE = "https://grml.org/"
LICENSE = "MIT"

_PV = "2021.07"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "\
https://download.grml.org/grml64-full_2021.07.iso;name=iso \
"
# SRC_URI[sha256sum.iso] = "71bb55c27ea5b881133bd558946c0b64193d596c1fbeea2ab344d66301d42465"

do_build[dirs] = "${B}"
do_build(){
    :
}
