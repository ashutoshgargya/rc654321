(function (window, angular, undefined) {

    angular.module('healthControllers').controller('registerCtrl', [
        '$scope', '$location', '$routeParams', '$log',
        function($scope, $location, $routeParams, $log) {

            $log.log( "loaded registerCtrl ..." );
	    
	    $scope.gotoPersonalInfo = function() {
		$location.path( '/personal_info' );
	    };
	    
	}]);

})(window, window.angular);

