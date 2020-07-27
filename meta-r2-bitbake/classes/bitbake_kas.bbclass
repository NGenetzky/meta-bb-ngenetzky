# TODO: Warn if "${B}" != "${WORKDIR}/build/"

KAS_FILES = "${WORKDIR}/kas.yml"
KAS_REPO_REF_DIR ??= "${DL_DIR}/git/"

# ENV for kas
export KAS_REPO_REF_DIR

kas_exec(){
    # TODO: Make it more flexbile
    python3 -m kas "$@"
}

kas_shell(){
    kas_exec shell "${KAS_FILES}"
}

kas_build(){
    kas_exec build "${KAS_FILES}"
}
