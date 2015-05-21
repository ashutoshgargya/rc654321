(function (window, angular, undefined) {

    var healthApp = angular.module('healthApp', [
        'ngRoute', 'ngSanitize', 'ngTouch', 'ngAnimate', 'ui.bootstrap',
        'healthControllers', 'healthServices', 'healthDirectives',
    ]);

    healthApp.config([
        '$routeProvider', '$locationProvider', '$httpProvider',
        function( $routeProvider, $locationProvider, $httpProvider ) {

            // $locationProvider.hashPrefix('!'); // necessary for prerendering and SEO

            $routeProvider
                .when('/home', {
                    templateUrl: 'src/partials/home.html',
                    controller: 'homeCtrl'
                })
                .when('/login', {
                    templateUrl: 'src/partials/login.html',
                    controller: 'loginCtrl'
                })
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
                .when('/prescriptions', {
                    templateUrl: 'src/partials/prescriptions.html',
                    controller: 'prescriptionsCtrl'
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
                    redirectTo: '/home'
                });
        }
    ]);
    
    healthApp.run( function( $rootScope, $log, $location ) {
        $rootScope.$on('$locationChangeSuccess', function(event, newUrl, oldUrl) {
            // Util.logEntryInit(); // any init functions here
        });
    });
    
})(window, window.angular);

