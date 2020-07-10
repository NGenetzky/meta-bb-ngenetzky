
# Recomend configuring at global scope
BB_VENV_WORKON ??= "${TOPDIR}/virtualenvs"
BB_VENV_DEFAULT ??= "default"

# Recomend configuring at package/recipe scope
BB_VENV_ENV ??= "${BB_VENV_DEFAULT}"
BB_VENV_VIRTUAL_ENV ??= "${BB_VENV_WORKON}/${BB_VENV_ENV}"

bb_venv_activate(){
    . "${BB_VENV_VIRTUAL_ENV}/bin/activate"
}

do_bootstrap[dirs] = "${BB_VENV_WORKON} ${B}"
bb_venv_do_bootstrap(){
    if [ ! -f "${BB_VENV_VIRTUAL_ENV}/bin/activate/" ] ; then
        python3 -m venv "${BB_VENV_VIRTUAL_ENV}"
    fi
    activate
}

EXPORT_FUNCTIONS do_bootstrap activate
