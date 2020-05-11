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
            all: {
                files: [{
                    src: 'dist/index.html',
                    dest: '../src/main/resource/static'
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
    grunt.registerTask('deploy', ['ts', 'copy:index', 'copy:all'])
}