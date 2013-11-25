// Generated on 2013-11-08 using generator-mobile 0.0.2
'use strict';

module.exports = function(grunt) {
    // show elapsed time at the end
    require('time-grunt')(grunt);
    // load all grunt tasks
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    // configurable paths
    var yeomanConfig = {
        app: 'app',
        dist: 'dist'
    };

    grunt.initConfig({
        yeoman: yeomanConfig,
        watch: {
            scripts: {
                files: ['<%= jshint.files %>'],
                tasks: [ 'build']
            }

        },
        // !! This is the name of the task ('requirejs')
        requirejs: {
            compile: {

                // !! You can drop your app.build.js config wholesale into 'options'
                options: {
                    baseUrl: './app/',
                    name: 'bower_components/almond/almond',
                    include: ['scripts/main'],
                    insertRequire: ['scripts/main'],
                    out: 'dist/scripts/main-built.js',
                    wrap: true,
                    findNestedDependencies: true,
                    mainConfigFile: 'app/scripts/buildconfig.js'
                }
            }
        },

        jshint: {
            options: {
                jshintrc: '.jshintrc'
            },
            files: [
                'Gruntfile.js',
                '<%= yeoman.app %>/scripts/{,*/}*.js',
                '!<%= yeoman.app %>/scripts/config.js',
                '!<%= yeoman.app %>/scripts/vendor/*'
            ]
        },
        clean: {
            dist: {
                files: [{
                    dot: true,
                    src: [
                        '.tmp',
                        '<%= yeoman.dist %>/scripts/*',
                        '!<%= yeoman.dist %>/.git*'
                    ]
                }]
            },
            server: '.tmp'
        }
    });
    grunt.registerTask('build', [
        'clean:dist',
        'requirejs'
    ]);

    grunt.registerTask('default', [
        'jshint',
        'watch:scripts'
    ]);

};
