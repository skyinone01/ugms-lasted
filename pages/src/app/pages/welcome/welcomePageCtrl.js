/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.welcome')
        .controller('WelcomePageCtrl', WelcomePageCtrl);

    /** @ngInject */
    function WelcomePageCtrl($scope, $filter,appBase) {

        $scope.welcomes = [];
        $scope.listWelcome=function(){
            appBase.doGet("welcomes",null,function(ret){
                 $scope.welcomes = ret.data.items;
            })
        }
        $scope.addWelcome = function(){
        }



    }

})();
