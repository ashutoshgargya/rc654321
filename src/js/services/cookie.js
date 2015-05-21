(function (window, angular, undefined) {

    angular.module('healthServices').service('Cookie', [
        '$http', '$log', 
        function($http, $log) {
            
            this.setCookie = function( key, val ) {
                if ( key && val ) {
                    $.cookie( key, val, { path: '/' });
                }
            };

            this.getCookie = function( key ) {
                if ( $.cookie( key )) {
                    return $.cookie( key );
                } else {
                    return "";
                }
            };

            this.deleteCookie = function( key ) {
                $.removeCookie( key, { path: '/' } );
            };
        }
    ]);
})(window, window.angular);
