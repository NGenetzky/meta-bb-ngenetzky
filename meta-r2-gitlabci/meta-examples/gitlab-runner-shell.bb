HOMEPAGE = "https://docs.gitlab.com/runner/"
PV = "13.7.0"
PR = "r0"

inherit bb_fetcher
addtask do_unpack before do_build

SRCNAME = "gitlab-runner-linux-amd64"
SRC_URI = "https://s3.amazonaws.com/gitlab-runner-downloads/v${PV}/binaries/${SRCNAME};name=amd64"
SRC_URI[amd64.sha256sum] = "191693ba7586862f6bfaef28d3e04132c0f2e261761573fde9f4cdf9cdd437f0"

GITLAB_RUNNER_EXEC ??= "${WORKDIR}/${SRCNAME}"
gitlab_runner_exec(){
    "${GITLAB_RUNNER_EXEC}" "$@"
}

do_build[dirs] = "${S}"
do_build(){
    chmod +x "${WORKDIR}/${SRCNAME}"

    # Sanity check
    gitlab_runner_exec --version
}
