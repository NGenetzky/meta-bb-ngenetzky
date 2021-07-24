inherit bb_fetch2_fetch
inherit bb_fetch2_unpack

addtask do_unpack before do_build
addtask do_fetch before do_unpack
