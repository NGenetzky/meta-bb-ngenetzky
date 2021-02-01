
require gh-microsoft-vscode-dev-containers.inc
PR = "${INC_PR}.0"

SRC_URI += "\
    file://Dockerfile;subdir=${PN} \
"

DOCKER_REPOSITORY ?= "${PN}"
DOCKER_TAG ?= "${PV}-${PR}"

exec_docker(){
    docker "$@"
}

# Keep source and build separate
B = "${WORKDIR}/${PN}/"

do_build[dirs] = "${B}"
do_build(){
    cp -nTR \
        "${S}/containers/debian/.devcontainer/" \
        "${B}/"
    exec_docker build \
        --tag "${DOCKER_REPOSITORY}:${DOCKER_TAG}" \
        --build-arg "VARIANT=debian-10" \
        './'

    exec_docker image inspect \
        "${DOCKER_REPOSITORY}:${DOCKER_TAG}"
}
