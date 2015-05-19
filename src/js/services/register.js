(function (window, angular, undefined) {

    angular.module('healthServices', ['ngResource'] );

    angular.module('healthServices').service('User', [
        '$http', '$log', 
        function($http, $log) {

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
                }).success(function(data, status, headers, config) {
                    if ( angular.isFunction( success )) {
                        success( data );
                    }
                }).error(function(data, status, headers, config) {
                    $log.log( "Error Data: ", data );
                    if ( angular.isFunction( failure )) {
                        failure( data );
                    }
                });
            };
        }
    ]);

})(window, window.angular);
