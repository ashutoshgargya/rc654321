(function (window, angular, undefined) {

    angular.module( 'healthControllers' ).controller( 'registerCtrl', [
        '$scope', '$location', '$routeParams', '$log', 'User',
        function( $scope, $location, $routeParams, $log, User ) {

            $log.log( "loaded registerCtrl ..." );
            $scope.user = {};

            $scope.insertUser = function() {
                var user = angular.copy( $scope.user );
                User.insertUser( 
                    user, 
                    function( data ) {
                        $location.path( '/personal_info' );                    
                    },
                    function( data ) {
                        $log.log( "Registration error" );
                        bootbox.alert( data.message );
                    }
                );
            };
	    }
    ]);

})(window, window.angular);

