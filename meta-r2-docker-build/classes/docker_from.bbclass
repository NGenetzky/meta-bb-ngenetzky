# NOTE: These values are only meant to be an example.
# TODO: Switch to values that cause failure unless overridden.
DOCKER_FROM_IMAGE ??= "phusion/baseimage"
DOCKER_FROM_TAG ??= "bionic-1.0.0"
DOCKER_FROM_DIGEST ??= "sha256:0481bb9b387564c43bd5d44cacf44efb492fd1e7bd1716e849262036b69c030c"

DOCKER_FROM ??= "${DOCKER_FROM_IMAGE}:${DOCKER_FROM_TAG}@${DOCKER_FROM_DIGEST}"

docker_from_pull[dirs] = "${B}"
docker_from_pull(){
    image_src="${DOCKER_FROM_IMAGE}:${DOCKER_FROM_TAG}"
    docker pull "${image_src}"
    docker image inspect "${image_src}" > "bb_docker_from.${PF}.docker_image.json"
}
