
# meta-bb-ngenetzky

## Known Issues

## bitbake-layers layerindex-fetch

Not all layers define vars required by `layerindex-fetch`.

```log
└─>bitbake-layers layerindex-fetch -b dunfell -n meta-poky
NOTE: Starting bitbake server...
Traceback (most recent call last):
  File "TOPDIR/../bitbake/bin/bitbake-layers", line 95, in <module>
    ret = main()
  File "TOPDIR/../bitbake/bin/bitbake-layers", line 88, in main
    return args.func(args)
  File "TOPDIR/../bitbake/lib/bblayers/layerindex.py", line 102, in do_layerindex_fetch
    apiurl = self.tinfoil.config_data.getVar('BBLAYERS_LAYERINDEX_URL').split()
AttributeError: 'NoneType' object has no attribute 'split'
```
