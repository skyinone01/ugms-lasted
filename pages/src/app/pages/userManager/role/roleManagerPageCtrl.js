/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.roleManager')
        .controller('RoleManagerPageCtrl', RoleManagerPageCtrl);

    angular.module('BlurAdmin.pages.userResource.roleManager')
        .controller('RoleResourceCtrl', RoleResourceCtrl);

    /** @ngInject */
    function RoleManagerPageCtrl($scope, $filter,appBase,$uibModal) {
        $scope.roles = [];
        $scope.roleUpdate = [];

        $scope.listRole=function(){
            appBase.doGet("common/roles",null,function(ret){
                 $scope.roles = ret.data;
                 $scope.roleUpdate = ret.data.slice(0);
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
            if(index >= $scope.roleUpdate.length){
                data = $scope.insert[index-$scope.roleUpdate.length];
            }else{
                data = $scope.roleUpdate[index];
            }
            if(data.name == null || data.name.trim() ==""){
                appBase.bubMsg("角色名字不能为空");
                $scope.listRole();
                return;
            }
            if(data.code == null || data.code.trim() ==""){
                appBase.bubMsg("角色Code不能为空");
                $scope.listRole();
                return;
            }
            if(data.description == null || data.description.trim() ==""){
                appBase.bubMsg("角色描述不能为空");
                $scope.listRole();
                return;
            }
            appBase.doPost("roles",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listRole();
            })
        };
        $scope.valueChange = function(parent,index){
            //var key = $(event.target).parent().parent().prev().attr("e-name");
            var key = parent.$editable.name;
            var data = parent.$data;
            if(index >= $scope.roleUpdate.length){
                 switch(key){
                       case "description":
                           $scope.insert[index-$scope.roleUpdate.length].description =data;
                             break;
                       case "name":
                           $scope.insert[index-$scope.roleUpdate.length].name =data;
                             break;
                       case "code":
                           $scope.insert[index-$scope.roleUpdate.length].code =data;
                             break;
                 }
            }else{
                switch(key){
                       case "description":
                           $scope.roleUpdate[index].description =data;
                             break;
                       case "name":
                           $scope.roleUpdate[index].name =data;
                             break;
                       case "code":
                           $scope.roleUpdate[index].code =data;
                           break;
                 }
            }
        };

        $scope.cancel = function(index){
            $scope.listRole();
        };

        $scope.deleteRole = function(index){

            appBase.doDelete("roles/"+$scope.roles[index].id,null,function(res){
                appBase.bubMsg("删除成功");
                $scope.listRole();
            })

        };

        $scope.permission = function (index) {
            var page = "app/pages/userManager/role/roleResource.html";
            var modalInstance  = $uibModal.open({
                animation: true,
                templateUrl: page,
                controller:'RoleResourceCtrl',
                size: 2000,
                resolve: {
                    index:function(){
                        return index;
                    },
                    roles:function(){
                        return $scope.roles;
                    }
                }
            });
        };
    }

    function RoleResourceCtrl($scope,roles,index,appBase) {

        $scope.visible = [{'value':'true','text':'true'},{'value':'false','text':'false'}]

        $scope.items=[];
        appBase.doGet("role/resources/?rid="+roles[index].id+"&perPage=50",null,function(ret){
            $scope.items = ret.data.items;
        })

        $scope.save = function(callback){

            appBase.doPut("role/resources/?rid="+roles[index].id,$scope.items,function(ret){
                appBase.bubMsg("保存成功");
                callback;
            })
        }
    }
})();
