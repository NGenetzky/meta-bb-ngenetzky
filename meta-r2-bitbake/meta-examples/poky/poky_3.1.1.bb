inherit bb_fetcher
addtask do_unpack before do_build

# febbe2944c (HEAD, tag: yocto-3.1.1, tag: dunfell-23.0.1)
SRCREV = "febbe2944c0c4a04b85fa98fdc261186115954d8"
SRC_URI = "git://git.yoctoproject.org/poky.git;branch=dunfell;destsuffix=${PN}-${PV}/"
SRC_URI += "file://site.conf"
