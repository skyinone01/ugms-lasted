/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.auth')
        .controller('AuthPageCtrl', AuthPageCtrl);

    /** @ngInject */
    function AuthPageCtrl($scope,$http) {

        $scope.submit = function() {
            var url = "http://127.0.0.1:8080/token?username="+$scope.username+"&password="+$scope.password;
            $http.get(url).success(function(res){
                if(res.errno == 0){
                    alert("success");
                    //setCookie("token",res.data.token)
                    //window.location.href ="http://localhost:3000";
                }else{
                    alert(res.error)
                }
            });
        };

    }

})();
