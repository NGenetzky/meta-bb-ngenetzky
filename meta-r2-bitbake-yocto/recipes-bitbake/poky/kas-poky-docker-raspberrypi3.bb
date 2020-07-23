SUMMARY = "Collection of kas configuration files"
HOMEPAGE = "https://github.com/texierp/kas-files"

PV = "2020-07-23"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build
SRCREV = "65b03fc80dffeee7fb1718420b20de5cef77579d"
SRC_URI = "git://github.com/texierp/kas-files.git;destsuffix=${PN}-${PV}/"

inherit bitbake_kas
KAS_FILES = "${S}/kas-poky.yml"
# KAS_FILES = "${S}/kas-poky.yml:${S}/kas-files/docker-raspberrypi3.yml" # TODO
