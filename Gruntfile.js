module.exports = function( grunt ) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        shell : {
            clean_health : {
                command : 'mkdir -p build/js; mkdir -p build/css; rm -rf build/js/*; rm -rf build/css/*;'
            },
        },

        preprocess: {
            health: {
                src        : 'src/partials/index.html.tmpl',
                dest       : 'index.html',
                options    : {
                    context : { ts: "<%= now %>" }
                }
            },
        },

        concat: {
            health    : { src: "<%= health_js_list %>", dest:'build/js/health_app_<%= now %>.js' },
            vendor_health : {
                src: "<%= vendor_health_js_list %>", dest:'build/js/vendor_libs_<%= now %>.js'
            },
            health_css: {
                src: "<%= health_css_list %>", dest: 'build/css/health_app_<%= now %>.css'
            },
        },

        uglify: {
            options    : { mangle: true, compress: false },
            health     : {
                options: { sourceMap: true, mangle: false, compress: false },
                src    : "<%= health_js_list %>", // make this work for uglify to replace concat                       
                dest   : 'build/js/health_app_<%= now %>.js'
            },
        },

        cssmin: {
            health: {
                files: {
                    'build/css/health_app_<%= now %>.css': "<%= health_css_list %>"
                }
            },
        },

        health_js_list: [
            "src/js/app.js",
            "src/js/modules.js",
            "src/js/services/user.js",
            "src/js/services/cookie.js",
            "src/js/services/browser.js",
            "src/js/controllers/home.js",
            "src/js/controllers/login.js",
            "src/js/controllers/paymentInfo.js",
            "src/js/controllers/personalInfo.js",
            "src/js/controllers/prescriptions.js",
            "src/js/controllers/register.js",
            "src/js/controllers/welcome.js",
            "src/js/directives/header.js",
        ],

        vendor_health_js_list: [
            "node_modules/jquery/dist/jquery.min.js",
            "node_modules/jquery.cookie/jquery.cookie.js",
            "node_modules/angular/angular.min.js",
            "node_modules/angular-resource/angular-resource.min.js",
            "node_modules/angular-route/angular-route.min.js",
            "node_modules/angular-sanitize/angular-sanitize.min.js",
            "node_modules/angular-touch/angular-touch.min.js",
            "node_modules/angular-animate/angular-animate.min.js",
            "node_modules/bootstrap/dist/js/bootstrap.min.js",
            "node_modules/angular-ui-bootstrap/dist/ui-bootstrap-tpls.js",
            "node_modules/bootbox/bootbox.min.js"
        ],

        health_css_list: [
            "src/css/bootstrap.css",
            "src/css/full.css",
        ],

        now: (new Date()).getTime()
    });

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks( 'grunt-contrib-uglify' );
    grunt.loadNpmTasks( 'grunt-contrib-concat' );
    grunt.loadNpmTasks( 'grunt-contrib-cssmin' );
    grunt.loadNpmTasks( 'grunt-preprocess' );
    grunt.loadNpmTasks( 'grunt-shell' );

       grunt.registerTask( 'default', 'Build', function() {
        var hostname = require("os").hostname();
        if ( hostname.match( /www/ )) {
            grunt.log.writeln( 'Running PRODUCTION build on ' + hostname + ' ...' );
            grunt.task.run([
                'shell:clean_health', 'preprocess',
                'uglify:health', // 'concat:health',
                'concat:vendor_health', 'concat:health_css',
            ]);
        } else {
            grunt.log.writeln( "Running development build on " + hostname + " ..." );
            grunt.task.run([
                'shell:clean_health', 'preprocess',
                'uglify:health', // 'concat:health',
                'concat:vendor_health', 'concat:health_css',
            ]);
        }
    });

};

