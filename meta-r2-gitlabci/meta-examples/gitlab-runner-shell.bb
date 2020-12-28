HOMEPAGE = "https://docs.gitlab.com/runner/"
PV = "13.7.0"
PR = "r1"

inherit bb_fetcher
addtask do_unpack before do_build

SRCNAME = "gitlab-runner-linux-amd64"
SRC_URI = "https://s3.amazonaws.com/gitlab-runner-downloads/v${PV}/binaries/${SRCNAME};name=amd64"
SRC_URI[amd64.sha256sum] = "191693ba7586862f6bfaef28d3e04132c0f2e261761573fde9f4cdf9cdd437f0"

do_unpack[postfuncs] += "gitlab_runner_unpack"
gitlab_runner_unpack(){
    chmod +x "${WORKDIR}/${SRCNAME}"
}

GITLAB_TOKEN ??= ""
GITLAB_RUNNER_EXEC ??= "${WORKDIR}/${SRCNAME}"

gitlab_runner_exec(){
    # --debug                      debug mode [$DEBUG]
    # --log-format value           Choose log format (options: runner, text, json) [$LOG_FORMAT]
    # --log-level value, -l value  Log level (options: debug, info, warn, error, fatal, panic) [$LOG_LEVEL]
    "${GITLAB_RUNNER_EXEC}" "$@"
}

addtask do_register before do_build
do_register(){
    # --locked # Lock Runner for current project, defaults to 'true' [$REGISTER_LOCKED]
    # --registration-token # Runner's registration token [$REGISTRATION_TOKEN]
    # --name # Runner name (default: "$(hostname)") [$RUNNER_NAME]
    # --builds-dir # Directory where builds are stored [$RUNNER_BUILDS_DIR]
    # --cache-dir # Directory where build cache is stored [$RUNNER_CACHE_DIR]
    # --run-untagged="false" \
    gitlab_runner_exec register \
        --non-interactive \
        --config "${S}/config.toml" \
        --url "https://gitlab.com/" \
        --registration-token "${GITLAB_TOKEN}" \
        --access-level="not_protected" \
        --executor "shell" \
        --tag-list "shell,${PF}" \
        --builds-dir "${B}"
}

inherit bb_build_shell
do_build_shell_scripts[nostamp] = "1"
addtask do_build_shell_scripts before do_build
python do_build_shell_scripts(){
    workdir = d.getVar('WORKDIR', expand=True)
    export_func_shell('do_server', d, os.path.join(workdir, 'run.do_server.sh'), workdir)
}

do_build[dirs] = "${S}"
do_build(){
    # Sanity check
    gitlab_runner_exec --version
}

################################################################################
# Extra tasks

addtask do_server
do_server(){
    gitlab_runner_exec run \
        --config "${S}/config.toml" \
        --working-directory "${B}"
}

addtask do_unregister
do_unregister(){
    # gitlab_runner_exec unregister --name ""
    gitlab_runner_exec unregister --all-runners
}

