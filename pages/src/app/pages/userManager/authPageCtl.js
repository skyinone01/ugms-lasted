/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('authCtl', AuthPageCtrl);

    /** @ngInject */
    function AuthPageCtrl($scope,$http,$location) {

        $scope.submit = function() {

            var url = "http://127.0.0.1:8080/demo/login?username="+$scope.username+"&password="+$scope.password;
            $http.get(url).success(function(res){
                $location.path("http://localhost:3000");
            });

        };
    }

})();
