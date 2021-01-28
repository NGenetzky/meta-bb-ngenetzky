# Copyright (C) 2020 Nathan Genetzky <nathan@genetzky.us>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "A minimal Ubuntu base image modified for Docker-friendliness"
HOMEPAGE = "https://github.com/phusion/baseimage-docker"
LICENSE = "MIT"

PR = "r1"

inherit docker_from
DOCKER_FROM_IMAGE = "phusion/baseimage"
DOCKER_FROM_TAG = "bionic-1.0.0"
DOCKER_FROM_DIGEST = "sha256:0481bb9b387564c43bd5d44cacf44efb492fd1e7bd1716e849262036b69c030c"
# https://hub.docker.com/layers/phusion/baseimage/bionic-1.0.0/images/sha256-0481bb9b387564c43bd5d44cacf44efb492fd1e7bd1716e849262036b69c030c?context=explore
# phusion/baseimage:bionic-1.0.0
# Digest:sha256:0481bb9b387564c43bd5d44cacf44efb492fd1e7bd1716e849262036b69c030c

do_build[dirs] = "${B}"
do_build(){
    docker_from_pull
}
