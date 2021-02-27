
require gh-microsoft-vscode-dev-containers.inc
PR = "${INC_PR}.1"

SRC_URI += "file://Dockerfile"

DOCKER_REPOSITORY ?= "${PN}"
DOCKER_TAG ?= "${PV}-${PR}"

# Keep source and build separate
B = "${WORKDIR}/${PN}/"

do_build[dirs] = "${WORKDIR}"
do_build(){
    install -d \
        "${B}/src/"

    cp -t "${B}/" \
        "${S}/containers/debian/.devcontainer/devcontainer.json" \
        "${WORKDIR}/Dockerfile"
    cp -t "${B}/src/" \
        "${S}/containers/debian/.devcontainer/library-scripts/common-debian.sh"

    docker_context="${PF}.docker_context.tar.xz"
    tar -ca \
        -f "$docker_context" \
        -C "${B}" \
        ./

    docker build \
        --tag "${DOCKER_REPOSITORY}:${DOCKER_TAG}" \
        --build-arg "INSTALL_ZSH=false" \
        --build-arg "UPGRADE_PACKAGES=false" \
        --build-arg "USERNAME=user" \
        --build-arg "VARIANT=buster" \
        "${B}"

    docker image inspect "${DOCKER_REPOSITORY}:${DOCKER_TAG}" > "${PF}.docker_image.json"
}
