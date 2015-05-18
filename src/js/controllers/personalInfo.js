(function (window, angular, undefined) {

    angular.module('healthControllers').controller('personalInfoCtrl', [
        '$scope', '$location', '$routeParams', '$log',
        function($scope, $location, $routeParams, $log) {

            $log.log( "loaded personalInfoCtrl ..." );
	    
	    $scope.gotoPaymentInfo = function() {
		$location.path( '/payment_info' );
	    };
	    
	}]);

})(window, window.angular);

