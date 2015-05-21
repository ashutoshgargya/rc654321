(function (window, angular, undefined) {

    angular.module('healthDirectives').directive( 'headerGeneral', [
        '$log', '$location', 'User',
        function( $log, $location, User ) {
            return {
                // restrict: "E",
                template: '<div class="navbar navbar-default navbar-fixed-top">
                             <div class="container">
                               <div class="navbar-header">
                                 <a href="#/home" class="navbar-brand">Revel Care</a>
                                 <button class="navbar-toggle" type="button" data-toggle="collapse"
                                         data-target="#navbar-main">
                                   <span class="icon-bar"></span>
                                   <span class="icon-bar"></span>
                                   <span class="icon-bar"></span>
                                 </button>
                               </div>
                               <div class="navbar-collapse collapse" id="navbar-main">
                                 <ul class="nav navbar-nav navbar-right">
                                   <li ng-if="config.isLoggedIn" style="cursor: pointer">
                                     <a href="#/prescriptions">Prescriptions</a>
                                   </li>
                                   <li ng-if="config.isLoggedIn" ng-click="logout()" style="cursor: pointer">
                                     <a>Logout</a>
                                   </li>
                                   <li ng-if="! config.isLoggedIn"><a href="#/login">Login</a></li>
                                 </ul>
                               </div>
                             </div>
                           </div>',
                link: function( scope ) {
                    scope.config  = {
                        isLoggedIn: User.getUserid() ? true : false,
                    };

                    scope.logout = function() {
                        User.logoutUser();
                        $location.path( '/home' );
                    };
                }
            };
        }
    ]);

})(window, window.angular);
