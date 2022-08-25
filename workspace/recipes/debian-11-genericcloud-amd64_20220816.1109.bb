# Copyright (C) 2021 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "debain-11 cloud-init image"
HOMEPAGE = "https://cdimage.debian.org/images/cloud/bullseye/"
LICENSE = "MIT"

_PV = "20220816-1109"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
_SRCNAME = "debian-11-genericcloud-amd64-20220816-1109"
SRC_URI = "\
https://cdimage.debian.org/images/cloud/bullseye/20220816-1109/${_SRCNAME}.qcow2;name=qcow2 \
https://cdimage.debian.org/images/cloud/bullseye/20220816-1109/${_SRCNAME}.json;name=json \
"
SRC_URI[sha256sum.qcow2] = "71bb55c27ea5b881133bd558946c0b64193d596c1fbeea2ab344d66301d42465"
SRC_URI[sha256sum.json] = "008b684c71bcceb51e1e457e078c8f8eeac9ba65ea97e40cc0ad90b993ed6689"

do_build[dirs] = "${B}"
do_build(){
    :
}
