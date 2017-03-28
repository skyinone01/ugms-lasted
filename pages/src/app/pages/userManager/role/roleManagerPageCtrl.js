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

            appBase.doGet("users/"+$scope.roles[index].id,null,function(ret){
                if(ret.data.length >0 ){
                    appBase.bubMsg("当前角色下还有用户，不能删除");
                    return;
                }else {
                    appBase.doDelete("roles/"+$scope.roles[index].id,function(ret){
                        appBase.bubMsg("删除成功");
                        $scope.listRole();
                    })
                }
            })

        };

        $scope.permission = function (index) {
            var page = "app/pages/userManager/role/roleResource.html";
            var modalInstance  = $uibModal.open({
                animation: true,
                templateUrl: page,
                controller:'RoleResourceCtrl',
                size: 1500,
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

    function RoleResourceCtrl($scope,$uibModal,$uibModalInstance,roles,index,appBase) {
        $scope.items=[];
        appBase.doGet("role/resources/?rid="+roles[index].id,null,function(ret){
            $scope.items = ret.data.items;
        })

        $scope.save = function(event,rIndex){
            var name = $($(event.target).parents('td')[0]).attr('name');
            if (name.trim() != "visible"){
                appBase.bubMsg("不能修复除|是否可见|的其他属性值");
                return;
            }
            var value = $(event.target).parent().prev().val();
            if (value.trim() != "true" && value.trim() != "false"){
                appBase.bubMsg("属性值只能为true 或者 false");
            }
            var update = $scope.items[rIndex];
            update.visible = value;

            appBase.doPut("role/resources/?rid="+roles[index].id,update,function(ret){
                appBase.bubMsg("保存成功");
            })
        }
    }
})();
