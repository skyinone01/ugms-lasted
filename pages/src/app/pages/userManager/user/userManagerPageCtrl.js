/**
 * @author roy
 * created on 03.12.2017
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.userManager')
        .controller('UserManagerPageCtrl', UserManagerPageCtrl);

    /** @ngInject */
    function UserManagerPageCtrl($scope, $filter,appBase) {

        $scope.smartTablePageSize = 10;
        $scope.users = [];
        $scope.role = [];
        $scope.update = [];

        $scope.listUser=function(){
            appBase.doGet("users",null,function(ret){
                 $scope.users = ret.data.items;
                 $scope.update = ret.data.items.slice(0);
            })
        }
        $scope.listRole=function(){
              appBase.doGet("roles",null,function(ret){
                  $scope.role = ret.data;
               })
         }
        $scope.listRole();
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
         //**user save
        $scope.insert = [];
        $scope.addUser = function() {
            $scope.inserted = {
                id: 0,
                username: '',
                name:'',
                mobile: '',
                department: '',
                email: '',
                role: null
            };
            $scope.users.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveUser = function(index){
            var data;
            if(index >= $scope.update.length){
                data = $scope.insert[index-$scope.update.length];
            }else{
                data = $scope.update[index];
            }
            appBase.doPost("users",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listUser();
            })
        };

        $scope.valueChange = function(parent,index){
            //var  name = $(event.target).parent().parent().prev().attr("e-name");
            var  name = parent.$editable.name;
            var  data = parent.$data;
            if(index >= $scope.update.length){
                 switch(name){
                       case "username":
                           $scope.insert[index-$scope.update.length].username =data;
                             break;
                       case "name":
                           $scope.insert[index-$scope.update.length].name =data;
                             break;
                       case "mobile":
                           $scope.insert[index-$scope.update.length].mobile =data;
                             break;
                       case "role":
                           $scope.insert[index-$scope.update.length].role =data;
                             break;
                     case "department":
                         $scope.insert[index-$scope.update.length].department =data;
                         break;
                     case "email":
                         $scope.insert[index-$scope.update.length].email =data;
                         break;
                 }
            }else{
                switch(name){
                       case "username":
                           $scope.update[index].username =data;
                             break;
                       case "role":
                           $scope.update[index].role =data;
                             break;
                       case "mobile":
                           $scope.update[index].mobile =data;
                           break;
                        case "department":
                            $scope.update[index].department =data;
                            break;
                        case "email":
                            $scope.update[index].email =data;
                            break;
                       case "name":
                           $scope.update[index].name =data;
                           break;
                 }
            }
        };

        $scope.cancelSave = function(index){

        };

        $scope.showRole = function(user) {
            if(user.role && $scope.role.length) {
                var selected = $filter('filter')($scope.role, {value: user.role});
                return selected.length ? selected[0].text : '未设置';
            } else return '未设置'
        };

        $scope.deleteUser = function(index){
            appBase.doDelete("users/"+$scope.users[index].id,null,function(ret){
                appBase.bubMsg("删除成功");
                $scope.removeUser(index);
             })
        };

    }

})();
