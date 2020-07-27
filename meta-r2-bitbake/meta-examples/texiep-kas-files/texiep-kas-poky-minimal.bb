require texiep-kas-poky.inc
kas_build(){
    kas_exec build --target 'core-image-minimal' "${KAS_FILES}"
}
