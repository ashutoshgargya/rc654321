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
            "src/js/controllers/welcome.js",
            "src/js/controllers/register.js",
            "src/js/controllers/personalInfo.js",
            "src/js/controllers/paymentInfo.js",
	    "src/js/services/welcome.js",
	],

	vendor_health_js_list: [
            "src/js/lib/misc/modernizr.min.js",
            "src/js/lib/jquery/jquery-1.11.3.min.js",
            "src/js/lib/jquery/jquery.cookie.js",
            "src/js/lib/angular/angular.min.js",
            "src/js/lib/angular/angular-resource.js",
            "src/js/lib/angular/angular-route.js",
            "src/js/lib/angular/angular-sanitize.js",
            "src/js/lib/angular/angular-touch.min.js",
            "src/js/lib/angular/angular-animate.js",
            "src/js/lib/bootstrap/bootstrap.min.js",
	    "src/js/lib/misc/ui-bootstrap-tpls.min.js", 
	],

	health_css_list: [
	    "src/css/bootstrap.css",
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

