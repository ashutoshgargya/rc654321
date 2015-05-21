(function (window, angular, undefined) {

    angular.module( 'healthControllers' ).controller( 'prescriptionsCtrl', [
        '$scope', '$location', '$routeParams', '$log', '$modal', 'User',
        function( $scope, $location, $routeParams, $log, $modal, User ) {

            $log.log( "loaded prescriptionsCtrl ..." );
            $scope.user = {
                id: User.getUserid(),
                prescriptions: [],
            };

            $scope.openNewPrescription = function() {
                bootbox.dialog({
                    title: "Enter a New Medicine",
                    message: '<div class="row">
                                <div class="col-md-12">
                                  <form class="form-horizontal">
                                    <div class="form-group">
                                      <label class="col-md-4 control-label" for="name">Name</label>
                                      <div class="col-md-4">
                                        <input id="name" name="name" type="text" placeholder="Your name"
                                               class="form-control input-md">
                                      </div>
                                    </div>
                                    <div class="form-group">
                                      <label class="col-md-4 control-label" for="awesomeness">
                                        How awesome is this?
                                      </label>
                                      <div class="col-md-4">
                                        <div class="radio">
                                          <label for="awesomeness-0">
                                            <input type="radio" name="awesomeness" id="awesomeness-0"
                                                   value="Really awesome" checked="checked">
                                            Really awesome
                                          </label>
                                        </div>
                                        <div class="radio">
                                          <label for="awesomeness-1">
                                            <input type="radio" name="awesomeness" id="awesomeness-1"
                                                   value="Super awesome">
                                              Super awesome
                                          </label>
                                        </div>
                                      </div>
                                    </div>
                                  </form>
                                </div>
                              </div>',
                    buttons: {
                        success: {
                            label: "Save",
                            className: "btn-success",
                            callback: function () {
                                var name = $('#name').val();
                                var answer = $("input[name='awesomeness']:checked").val()
                                Example.show("Hello " + name + ". You've chosen <b>" + answer + "</b>");
                            }
                        }
                    }
                });
            };

            $scope.getPrescriptions = function() {
                User.getPrescriptions( 
                    $scope.user, 
                    function( data ) {
                        
                    },
                    function( data ) {
                        $log.log( "Prescriptions" );
                        bootbox.alert( data.message );
                    }
                );
            };

	    }
    ]);

})(window, window.angular);

