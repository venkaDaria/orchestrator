(function () {
    Error.stackTraceLimit = Infinity;

    jasmine.DEFAULT_TIMEOUT_INTERVAL = 3000;

    System.config({
        map: {
            '@angular/core/testing': 'npm:@angular/core/bundles/core-testing.umd.js',
            '@angular/common/testing': 'npm:@angular/common/bundles/common-testing.umd.js',
            '@angular/compiler/testing': 'npm:@angular/compiler/bundles/compiler-testing.umd.js',
            '@angular/platform-browser/testing': 'npm:@angular/platform-browser/bundles/platform-browser-testing.umd.js',
            '@angular/platform-browser-dynamic/testing': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic-testing.umd.js',
            '@angular/router/testing': 'npm:@angular/router/bundles/router-testing.umd.js',
            '@angular/forms/testing': 'npm:@angular/forms/bundles/forms-testing.umd.js'
        }
    });

    System.import('systemjs.config.js')
        .then(initTestBed)
        .then(initTesting);

    function initTestBed() {
        return Promise.all([
            System.import('@angular/core/testing'),
            System.import('@angular/platform-browser-dynamic/testing')
        ]).then(initTestEnvironment);

        function initTestEnvironment(providers) {
            var coreTesting = providers[0];
            var browserTesting = providers[1];

            coreTesting.TestBed.initTestEnvironment(
                browserTesting.BrowserDynamicTestingModule,
                browserTesting.platformBrowserDynamicTesting()
            );
        }
    }

    function initTesting() {
        console.log('loading spec files: ' + specFiles.join(', '));

        return Promise.all(specFiles.map(specImport))
            .then(success, console.error.bind(console));

        function specImport(spec) {
            return System.import(spec);
        }

        function success() {
            console.log('Spec files loaded; starting Jasmine test-runner');
            window.onload();
        }
    }
})();