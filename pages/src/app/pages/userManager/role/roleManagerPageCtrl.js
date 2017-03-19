/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.roleManager')
        .controller('RoleManagerPageCtrl', RoleManagerPageCtrl);

    /** @ngInject */
    function RoleManagerPageCtrl($scope, $filter,appBase,$uibModal) {
        $scope.roles = [];
        $scope.roleUpdate = [];

        $scope.listRole=function(){
            appBase.doGet("common/roles",null,function(ret){
                 $scope.roles = ret.data;
                 $scope.roleUpdate = ret.data;
            })
        }
        $scope.listRole();

        $scope.removeRole = function(index) {
            $scope.roles.splice(index, 1);
        };
         //**role save
        $scope.insert = [];
        $scope.addRole = function() {
            $scope.inserted = {
                id: 0,
                name: '',
                code:'',
                description: ''
            };
            $scope.roles.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveRole = function(index){
            var data;
            if(index >= $scope.roles.length){
                data = $scope.insert[index];
            }else{
                data = $scope.roleUpdate[index];
            }
            appBase.doPost("roles",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listRole();
            })
        };
        $scope.valueChange = function(event,index){
            var key = $(event.target).parent().parent().prev().attr("e-name");
            if(index >= $scope.roleUpdate.length){
                 switch(key){
                       case "description":
                           $scope.insert[index].description =event.target.value;
                             break;
                       case "name":
                           $scope.insert[index].name =event.target.value;
                             break;
                       case "code":
                           $scope.insert[index].code =event.target.value;
                             break;
                 }
            }else{
                switch(key){
                       case "description":
                           $scope.roleUpdate[index].description =event.target.value;
                             break;
                       case "name":
                           $scope.roleUpdate[index].name =event.target.value;
                             break;
                       case "code":
                           $scope.roleUpdate[index].code =event.target.value;
                           break;
                 }
            }
        };

        $scope.cancelRole = function(index){

        };

        $scope.deleteRole = function(index){
            appBase.doPost("roles/delete",$scope.roles[index],function(ret){
                appBase.bubMsg("删除成功");
                $scope.removeUser(index);
             })
        };

        $scope.permissions=[];

        $scope.permission = function (index) {
              appBase.doGet("role/resources/?rid="+$scope.roles[index].id,null,function(ret){
                   $scope.permissions = ret.data.items;
                   var page = "app/pages/userManager/role/roleResource.html";
                   $uibModal.open({
                     animation: true,
                     templateUrl: page,
                     size: 1500,
                     resolve: {
                       items: function () {
                         return $scope.permissions;
                       }
                     }
                   });
              })


        };
    }
})();
