(function (window, angular, undefined) {

    var healthApp = angular.module('healthApp', [
        'ngRoute', 'ngSanitize', // 'ngTouch', 'ngAnimate', 'ui.bootstrap',
	'healthControllers', // 'healthServices'
    ]);

    healthApp.config([
        '$routeProvider', '$locationProvider', '$httpProvider',
        function( $routeProvider, $locationProvider, $httpProvider ) {

            // $locationProvider.hashPrefix('!'); // necessary for prerendering and SEO

            $routeProvider
                .when('/register', {
                    templateUrl: 'src/partials/register.html',
                    controller: 'registerCtrl'
                })
		.when('/personal_info', {
                    templateUrl: 'src/partials/personalInfo.html',
                    controller: 'personalInfoCtrl'
                })
		.when('/payment_info', {
                    templateUrl: 'src/partials/paymentInfo.html',
                    controller: 'paymentInfoCtrl'
                })
                .when('/welcome', {
                    templateUrl: 'src/partials/welcome.html',
                    controller: 'welcomeCtrl'
                })
		.when('/request_invite', {
                    templateUrl: 'src/partials/request_invite.html',
                    // controller: 'welcomeCtrl'
                })
	        .otherwise({
                    redirectTo: '/register'
                });
        }
    ]);
    
    healthApp.run( function( $rootScope, $log, $location ) {
        $rootScope.$on('$locationChangeSuccess', function(event, newUrl, oldUrl) {
	    
	    // Util.logEntryInit(); // any init functions here
        });
    });
    
})(window, window.angular);

