INC_PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build

PV ??= "2021-01-29"
SRCREV = "e8090f6ce89cc71d2b50c23135d3cc57fb8765ac"
SRC_URI = "git://github.com/microsoft/vscode-dev-containers.git;destsuffix=${PN}-${PV};protocol=https"
S = "${WORKDIR}/${PN}-${PV}"
