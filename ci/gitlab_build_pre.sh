#!/bin/bash -xe
[ -d bitbake ] \
    || git clone \
        "https://github.com/openembedded/bitbake" \
        'bitbake'
mkdir -p 'build/tmp/deploy/'
mkdir -p 'build/conf/'
cp -T \
    'conf/bblayers.conf' \
    'build/conf/bblayers.conf'