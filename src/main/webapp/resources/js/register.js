/**
 * Register app sign up form
 */

var registerApp = angular.module('registerApp', [])
.factory('apiService', function($http) {
    return {
        save: function( account) {
            return $http.post("api/account/", account).then(
                function success(response) {
                    return response.data;
                },
                function error(response) {
                    throw response.data;
                }
            );
        }
    }
})
.controller('signupController', function($scope, apiService) {
    $scope.account = { username: '', email: '', password: ''};

    $scope.validationErrors = function(field) {
        var errors = $scope.signupForm[ field].$error;
        var result = [];
        for(var error in errors) {
            if( errors[error]) {
                result.push(error);
            }
        };
        var msg = result.join(", ");
        return msg.charAt(0).toUpperCase() + msg.slice(1);
    }

    $scope.save = function(form) {
        apiService.save( $scope.account)
            .then( function success( saved) {
                bootbox.dialog({
                    title: "Welcome!",
                    message: "You have been registered.",
                    closeButton: false,
                    buttons: {
                        main: {
                            label: "Register again",
                            className: "btn-primary",
                            callback: function() { location.reload() }
                        }
                    }
                });
            })
            .catch( function error( exception) {
                    if( exception.errors) {
                        // clean up previous validation
                        for(var field in $scope.signupForm) {
                            var previousValidations = $scope.signupForm[ field].$error;
                            if( angular.isDefined( previousValidations)) {
                                for(var validation in previousValidations) {
                                    $scope.signupForm[ field].$setValidity( validation, true)
                                }
                            }
                        }
                        for (var field in exception.errors) {
                            if (exception.errors.hasOwnProperty(field)) {
                                $scope.signupForm[ field].$dirty = true;
                                $scope.signupForm[ field].$setValidity(exception.errors[field], false)
                            }
                        }
                    }

                noty({
                    text: exception.message,
                    type: 'error',
                    timeout: 5000
                });
            })
    }

});