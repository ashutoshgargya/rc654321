(function (window, angular, undefined) {

    angular.module('healthControllers').controller('paymentInfoCtrl', [
        '$scope', '$location', '$routeParams', '$log', 'User',
        function( $scope, $location, $routeParams, $log, User ) {

            $log.log( "loaded paymentInfoCtrl ..." );
            $scope.config  = {
                isLoggedIn: User.getUserid() ? true : false,
            };
            $scope.user    = {};
            $scope.user.id = User.getUserid();

            var handler = StripeCheckout.configure({
                key: 'pk_test_9ZvHQY5eLRYdrAQkiMWWiSvr',
                // image: '/img/documentation/checkout/marketplace.png',
                token: function(token) {
                    // Use the token to create the charge with a server-side script.
                    // You can access the token ID with `token.id`
                    $log.log( "Received token: ", token );
                }
            });

            $('#customButton').on('click', function(e) {
                // Open Checkout with further options
                handler.open({
                    name: 'Revelcare',
                    description: 'For future use',
                    amount: 0
                });
                e.preventDefault();
            });

            // Close Checkout on page navigation
            $(window).on('popstate', function() {
                handler.close();
            });
	    
            $scope.gotoHome = function() {
                $location.path( '/home' );
            };
	    }
    ]);

})(window, window.angular);

