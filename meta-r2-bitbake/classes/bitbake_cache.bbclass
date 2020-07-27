# TODO: Would rather make more generic class, or reuse sstate-cache functionality

BITBAKE_CACHE_DIR ??= "${TOPDIR}/var/cache"

BITBAKE_CACHE_KEY ??= "${PF}"
BITBAKE_CACHE_KEY_PULL ??= "${BITBAKE_CACHE_KEY}"
BITBAKE_CACHE_KEY_PUSH ??= "${BITBAKE_CACHE_KEY}"

# a=archive, i=itemize, v=verbose, n=dry-run
BITBAKE_CACHE_RSYNC_OPTS ??= "-a"
BITBAKE_CACHE_RSYNC_EXTRAS ??= "--stats"


do_bitbake_cache_push[nostamp] = "1"
do_bitbake_cache_push[dirs] = "\
    ${BITBAKE_CACHE_DIR} \
    ${WORKDIR} \
"
addtask do_bitbake_cache_push
do_bitbake_cache_push(){
    cache_key="${BITBAKE_CACHE_KEY_PUSH}"
    if [ -z "${cache_key}" ] ; then
        bbwarn "bitbake_cache: push disabled"
        exit 0
    fi

    d_cache="${BITBAKE_CACHE_DIR}/${cache_key}"
    if [ -d "${d_cache}" ] ; then
        bbnote "bitbake_cache: push replacing existing cache"
    else
        install -d "${d_cache}"
    fi

    d_downloads="${B}/downloads"
    d_sstate="${B}/sstate-cache"

    if [ -d "$d_downloads" ] ; then
        bbnote "bitbake_cache: push '${BITBAKE_CACHE_KEY_PUSH}/downloads'"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" ${BITBAKE_CACHE_RSYNC_EXTRAS} --delete \
            "${d_downloads}/" \
            "${d_cache}/downloads"
    fi

    if [ -d "$d_sstate" ] ; then
        bbnote "bitbake_cache: push '${BITBAKE_CACHE_KEY_PUSH}/sstate-cache'"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" ${BITBAKE_CACHE_RSYNC_EXTRAS} --delete \
            "${d_sstate}/" \
            "${d_cache}/sstate-cache"
    fi
}


do_bitbake_cache_pull[nostamp] = "1"
do_bitbake_cache_pull[dirs] = "\
    ${BITBAKE_CACHE_DIR} \
    ${WORKDIR} \
"
addtask do_bitbake_cache_pull
do_bitbake_cache_pull(){
    cache_key="${BITBAKE_CACHE_KEY_PULL}"
    if [ -z "${cache_key}" ] ; then
        bbwarn "bitbake_cache: pull disabled"
        exit 0
    fi

    d_cache="${BITBAKE_CACHE_DIR}/${cache_key}"
    if [ ! -d "${d_cache}" ] ; then
        bbwarn "bitbake_cache: pull cache miss"
        exit 0
    fi

    d_downloads="${B}/downloads"
    d_sstate="${B}/sstate-cache"

    if [ -d "${d_cache}/downloads/" ] ; then
        bbnote "bitbake_cache: pull '${BITBAKE_CACHE_KEY_PULL}/downloads'"
        install -d "${d_downloads}"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" ${BITBAKE_CACHE_RSYNC_EXTRAS} --delete \
            "${d_cache}/downloads/" \
            "${d_downloads}"
    fi

    if [ -d "${d_cache}/sstate-cache/" ] ; then
        bbnote "bitbake_cache: pull '${BITBAKE_CACHE_KEY_PULL}/sstate-cache'"
        install -d "${d_sstate}"
        # NOTE: Ensure SRC has trailing slash.
        rsync "${BITBAKE_CACHE_RSYNC_OPTS}" ${BITBAKE_CACHE_RSYNC_EXTRAS} --delete \
            "${d_cache}/sstate-cache/" \
            "${d_sstate}"
    fi
}
