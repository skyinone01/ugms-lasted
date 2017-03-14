/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userManager')
        .controller('UserManagerPageCtrl', UserManagerPageCtrl);

    function getCookie(name)
    {
        var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

        if(arr=document.cookie.match(reg))

            return unescape(arr[2]);
        else
            return null;
    }

    /** @ngInject */
    function UserManagerPageCtrl($scope, $filter,$http,$stateProvider, $urlRouterProvider) {

        $scope.smartTablePageSize = 10;
        $scope.users = [];

        $scope.role = [
            {value: 1, text: '系统管理'},
            {value: 2, text: '经理'},
            {value: 3, text: '职员'}
        ];

        $scope.listUser=function(){

            $http({
                method: "GET",
                url: "http://127.0.0.1:8080/demo/users",
                headers: {"token":getCookie("token")}
            }).success(function(res){
                $scope.users = res;
            });

        }

        $scope.listUser();

        $scope.showStatus = function(user) {
            var selected = [];
            if(user.role) {
                selected = $filter('filter')($scope.role, {value: user.role});
            }
            return selected.length ? selected[0].text : '未设置';
        };


        $scope.removeUser = function(index) {
            $scope.users.splice(index, 1);
        };

        $scope.addUser = function() {
            $scope.inserted = {
                id: 0,
                username: '',
                name:'',
                mobile: '',
                role: null
            };
            $scope.users.push($scope.inserted);
        };

        $scope.saveUser = function(index){
            $http({
                method: "POST",
                url: "http://127.0.0.1:8080/demo/users",
                params: $scope.users[index]
            }).success(function(res){
                alert("保存成功")
                $scope.listUser();
            });
        };

        $scope.deleteUser = function(index){

            $http({
                method: "POST",
                url: "http://127.0.0.1:8080/demo/users/delete",
                params: $scope.users[index]
            }).success(function(res){
                alert("删除成功")
                $scope.removeUser(index)
            });
        };


    }

})();
