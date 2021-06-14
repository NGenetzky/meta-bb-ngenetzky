# Copyright (C) 2020 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Manage remote repository clones"
HOMEPAGE = "https://github.com/x-motemen/ghq"
LICENSE = "MIT"
SECTION = "devtool"

PV = "1.1.4"
PR = "r1"

SRCNAME = "ghq_linux_amd64"

inherit bb_fetcher
addtask do_unpack before do_build
SRC_URI = "https://github.com/x-motemen/ghq/releases/download/v${PV}/${SRCNAME}.zip"

SRC_URI[md5sum] = "b27b5ca25cd97fa47570fb3cb7a971b9"
SRC_URI[sha256sum] = "cfcd3e3d9b66a1fa0f763be587462b7b99638a9413ab80daaf99bff688cefec3"

S = "${WORKDIR}/${SRCNAME}"

inherit bb_venv
addtask do_bootstrap after do_unpack before do_build
D = "${BB_VENV_VIRTUAL_ENV}"

do_build[dirs] = "${S}"
do_build(){
    install -t "${D}/bin/" \
        "${S}/ghq"
}
