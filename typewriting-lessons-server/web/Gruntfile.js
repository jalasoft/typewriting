module.exports = function(grunt) {
    grunt.initConfig({
        ts: {
            default: {
                tsconfig: './tsconfig.json'
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
            files: ['src/ts/**.ts', 'src/index.html'],
            tasks: [
                "default"
            ]
        }
    });
    
    grunt.loadNpmTasks('grunt-ts')
    grunt.loadNpmTasks('grunt-contrib-watch')
    grunt.loadNpmTasks('grunt-contrib-copy')

    grunt.registerTask('default', ['ts', 'copy:index'])
    grunt.registerTask('deploy', ['ts', 'copy:index', 'copy:deploy'])
}