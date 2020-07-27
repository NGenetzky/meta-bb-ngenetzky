# TODO: Would rather make more generic class, or reuse sstate-cache functionality

BITBAKE_CACHE_ROOT ??= "${TOPDIR}/var/cache/"
BITBAKE_CACHE_KEY ??= "${PF}"
BITBAKE_CACHE_DIR ??= "${BITBAKE_CACHE_ROOT}/${BITBAKE_CACHE_KEY}"

# a=archive, i=itemize, v=verbose
BITBAKE_CACHE_RSYNC_OPTS ??= "-ai"

do_bitbake_cache_push[nostamp] = "1"
do_bitbake_cache_push[dirs] = "\
    ${BITBAKE_CACHE_DIR} \
    ${WORKDIR} \
"
addtask do_bitbake_cache_push
do_bitbake_cache_push(){
    install -d \
        "${BITBAKE_CACHE_DIR}/downloads" \
        "${BITBAKE_CACHE_DIR}/sstate-cache"

    d_downloads="${B}/downloads"
    if [ -d "$dl_dir" ] ; then
        bbnote "bitbake_cache: push '${BITBAKE_CACHE_KEY}/downloads'"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" --delete \
            "${d_downloads}/" \
            "${BITBAKE_CACHE_DIR}/downloads"
    fi

    d_sstate="${B}/sstate-cache"
    if [ -d "$d_sstate" ] ; then
        bbnote "bitbake_cache: push '${BITBAKE_CACHE_KEY}/sstate-cache'"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" --delete \
            "${d_sstate}/" \
            "${BITBAKE_CACHE_DIR}/sstate-cache"
    fi
}
