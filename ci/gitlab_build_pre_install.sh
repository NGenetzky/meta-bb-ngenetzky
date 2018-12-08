#!/bin/bash -xe
[ -d bitbake ] \
    || git clone \
        "https://github.com/openembedded/bitbake" \
        'bitbake'
mkdir -p 'layers/'
[ -d 'layers/meta-ngenetzky' ] \
    || git clone \
        "https://github.com/NGenetzky/meta-ngenetzky.git" \
        'layers/meta-ngenetzky'
mkdir -p 'build/conf/'
cp -T \
    'conf/bblayers.conf' \
    'build/conf/bblayers.conf'