BITBAKE_OE_ROOT = "${S}"
BITBAKE_OE_FILE ??= "oe-init-build-env"

# TODO: We should only need '[dirs]' or 'cd' not both.
# NOTE: We specify 'dirs' here to handle shells that can't determine path to oe-init.
oe_init_build_env[dirs] = "${BITBAKE_OE_ROOT}"
oe_init_build_env(){
    cd "${BITBAKE_OE_ROOT}"

    # TODO: Do proper store and resume
    set +e

    . "${BITBAKE_OE_ROOT}/${BITBAKE_OE_FILE}" \
        "${B}" \
        "${BITBAKE_OE_ROOT}/bitbake"

    # TODO: Do proper store and resume
    set -e
}
