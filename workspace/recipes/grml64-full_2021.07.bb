
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
SRC_URI[iso.sha256sum] = "7cec5cb3816edd55164d8d1cdfc315ecdda1ea26e5daa52fc8462f8d5bdd888d"

do_build[dirs] = "${B}"
do_build(){
    :
}
