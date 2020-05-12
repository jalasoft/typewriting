module.exports = function(grunt) {

    const sass = require('node-sass');
    require('load-grunt-tasks')(grunt);

    grunt.initConfig({
        ts: {
            default: {
                tsconfig: './tsconfig.json'
            }
        },
        sass: {
            options: {
                implementation: sass,
                sourceMap: true
            },
            dist : {
                files: {
                    'dist/css/styles.css': 'src/sass/*.scss'
                }
            }
        },
        copy: {
            index: {
                files: [{
                    src: 'src/index.html',
                    dest: 'dist/index.html'
                }]
            },
            deploy: {
                files: [{
                    expand: true,
                    cwd: 'dist',
                    src: '**',
                    dest: '../src/main/resources/static/'
                }]
            }
        },
        watch: {
            files: ['src/ts/**.ts', 'src/sass/*.scss', 'src/index.html'],
            tasks: [
                "default"
            ]
        }
    });
    
    grunt.loadNpmTasks('grunt-ts')
    grunt.loadNpmTasks('grunt-contrib-watch')
    grunt.loadNpmTasks('grunt-contrib-copy')
    
    grunt.registerTask('default', ['ts', 'sass', 'copy:index'])
    grunt.registerTask('deploy', ['ts', 'copy:index', 'copy:deploy'])
}