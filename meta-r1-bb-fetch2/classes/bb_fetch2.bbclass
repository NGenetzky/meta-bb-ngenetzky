inherit bb_fetch2_fetch
inherit bb_fetch2_unpack
addtask unpack after do_fetch
addtask do_unpack before do_build
