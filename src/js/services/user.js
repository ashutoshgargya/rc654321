(function (window, angular, undefined) {

    angular.module( 'healthServices').service( 'User', [
        '$http', '$log', 'Cookie',
        function( $http, $log, Cookie ) {

            var that = this;

            this.insertUser = function( user, success, failure ) {
                if ( ! user.email_address ) {
                    bootbox.alert( "Please enter an email address." );
                    return;
                }
                if ( ! user.password ) {
                    bootbox.alert( "Please enter a password." );
                    return;
                }
                if ( ! user.repeat_password ) {
                    bootbox.alert( "Please re-enter your password." );
                    return;
                }
                if ( user.password !== user.repeat_password ) {
                    bootbox.alert( "Please make sure your passwords match." );
                    return;
                }
                delete user.repeat_password;

                // Data looks good, attempt to insert
                var url     = 'https://www.revelcare.com/api/api.php';
                user.action = 'insertUser';
                $http({
                    method: 'POST',
                    url: url,
                    data: $.param( user ),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success( function( data, status, headers, config ) {
                    if ( angular.isFunction( success )) {
                        success( data );
                    }
                }).error( function( data, status, headers, config ) {
                    $log.log( "Error Data: ", data );
                    if ( angular.isFunction( failure )) {
                        failure( data );
                    }
                });
            };

            this.updateUser = function( user, success, failure ) {
                if ( ! user.full_name ) {
                    bootbox.alert( "Please enter your full name." );
                    return;
                }
                if ( ! user.phone_number ) {
                    bootbox.alert( "Please enter your phone number." );
                    return;
                }
                if ( ! user.dob ) {
                    bootbox.alert( "Please re-enter your date of birth." );
                    return;
                }
                that.updateUserToServer( user, success, failure );
            };
            
            this.updateUserToServer = function( user, success, failure ) {
                // Data looks good, attempt to update
                var url     = 'https://www.revelcare.com/api/api.php';
                user.action = 'updateUser';
                $http({
                    method: 'POST',
                    url: url,
                    data: $.param( user ),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success( function( data, status, headers, config ) {
                    if ( angular.isFunction( success )) {
                        success( data );
                    }
                }).error( function( data, status, headers, config ) {
                    $log.log( "Error Data: ", data );
                    if ( angular.isFunction( failure )) {
                        failure( data );
                    }
                });
            };

            this.getPaymentDetails = function( user, success, failure ) {
                var url     = 'https://www.revelcare.com/api/api.php';
                user.action = 'getPaymentDetails';
                $http({
                    method: 'POST',
                    url: url,
                    data: $.param( user ),
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success( function( data, status, headers, config ) {
                    if ( angular.isFunction( success )) {
                        success( data );
                    }
                }).error( function( data, status, headers, config ) {
                    if ( angular.isFunction( failure )) {
                        failure( data );
                    }
                });
            };

            this.loginUser = function( data ) {
                var id = data['_id']['$id'];
                if ( id ) {
                    Cookie.setCookie( 'id', id );
                }
            };

            this.getUserid = function() {
                return Cookie.getCookie( 'id' );
            };

        }
    ]);

})(window, window.angular);
