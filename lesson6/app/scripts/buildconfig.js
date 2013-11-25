require.config({
    paths: {
        zepto: 'scripts/vendor/zepto.min',
        core: 'scripts/core'
    },
    shim: {
        zepto: {
            exports: '$'
        }
    }
});