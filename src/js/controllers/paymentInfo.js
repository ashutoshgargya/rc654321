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

            Stripe.setPublishableKey('pk_test_6pRNASCoBOKtIshFeQd4XMUh');


            var handler = StripeCheckout.configure({
                key: 'pk_test_9ZvHQY5eLRYdrAQkiMWWiSvr',
                // image: '/img/documentation/checkout/marketplace.png',
                token: function(token) {
                    // Use the token to create the charge with a server-side script.
                    // You can access the token ID with `token.id`
                    $log.log( "Received token: ", token );
                }
            });

            $('#payment-form').submit( function( event ) {
                var $form = $(this);
                // Disable the submit button to prevent repeated clicks
                $form.find('button').prop('disabled', true);
                Stripe.card.createToken($form, stripeResponseHandler);
                // Prevent the form from submitting with the default action
                return false;
            });

            function stripeResponseHandler(status, response) {
                $log.log( "STATUS: ", status, " RESPONSE: ", response );
                var $form = $('#payment-form');
                if ( response.error ) {
                    // Show the errors on the form
                    bootbox.alert( response.error.message );
                    $form.find('button').prop('disabled', false);
                } else {
                    // response contains id and card, which contains additional card details
                    var token = response.id;
                    // Insert the token into the form so it gets submitted to the server
                    // $form.append($('<input type="hidden" name="stripeToken" />').val(token));
                    // and submit
                    // $form.get(0).submit();
                    $log.log( "ID = " + response.id );
                    var user = { payment_details: response, id: User.getUserid() };
                    User.updateUserToServer( 
                        user,
                        function( data ) {
                            // $location.path( '/register' );
                            User.getPaymentDetails(
                                user,
                                function( data ) {
                                    $log.log( "PAYMENT DETAILS: ", data );
                                },
                                function( data ) {
                                    
                                }
                            );
                        },
                        function( data ) {
                            $log.log( "Update user error" );
                            bootbox.alert( data.message );
                        }
                    );
                }
            };

            /*
            handler.open({
                name: 'Revelcare',
                description: '',
                amount: 0
            });
            */

            /*
            $('#customButton').on('click', function(e) {
                // Open Checkout with further options
                handler.open({
                    name: 'Revelcare',
                    description: '',
                    amount: 0
                });
                e.preventDefault();
            });
            */

            // Close Checkout on page navigation
            $(window).on('popstate', function() {
                handler.close();
            });
	    
	    }
    ]);

})(window, window.angular);

