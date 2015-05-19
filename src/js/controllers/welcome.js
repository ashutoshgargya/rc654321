(function (window, angular, undefined) {

    angular.module( 'healthControllers', [] );

    angular.module('healthControllers').controller('welcomeCtrl', [
        '$scope', '$location', '$routeParams', '$log',
        function($scope, $location, $routeParams, $log) {

            $log.log( "loaded welcomeCtrl ..." );

            $scope.requestInvite = function() {
                $location.path( '/request_invite' );
            };

        }
    ]);

})(window, window.angular);

