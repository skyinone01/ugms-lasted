/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.resourceManager')
        .controller('ResourceManagerPageCtrl', ResourceManagerPageCtrl);


    /** @ngInject */
    function ResourceManagerPageCtrl($scope, $filter,appBase) {
        $scope.resources = [];
        $scope.update = [];

        $scope.listResource=function(){
            var param = "page="+1+"&perPage="+50;
            appBase.doGet("resources?"+param,null,function(ret){
                 $scope.resources = ret.data.items;
                 $scope.update = ret.data.items.slice(0);
            })
        }
        $scope.listResource();

        $scope.removeResource = function(index) {
            $scope.resources.splice(index, 1);
        };
         //**resource save
        $scope.insert = [];
        $scope.addResource = function() {
            $scope.inserted = {
                id: 0,
                name: '',
                state:'',
                url: '',
                description: ''
            };
            $scope.resources.push($scope.inserted);
            $scope.insert.push($scope.inserted);
        };
        $scope.saveResource = function(index){
            var data;
            if(index >= $scope.update.length){
                data = $scope.insert[index-$scope.update.length];
            }else{
                data = $scope.update[index];
            }
            if(data.name == null || data.name.trim() ==""){
                appBase.bubMsg("资源名称不能为空");
                $scope.listResource();
                return;
            }
            if(data.description == null || data.description.trim() ==""){
                appBase.bubMsg("描述不能为空");
                $scope.listResource();
                return;
            }
            if(data.state == null || data.state.trim() ==""){
                appBase.bubMsg("前端路由不能为空");
                $scope.listResource();
                return;
            }
            if(data.url == null || data.url.trim() ==""){
                appBase.bubMsg("匹配URI不能为空");
                $scope.listResource();
                return;
            }
            appBase.doPost("resources",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listResource();
            })
        };

        $scope.valueChange = function(parent,index){

            //var name = $(event.target).parent().parent().prev().attr("e-name");
            var name = parent.$editable.name;
            var data = parent.$data;
            if(index >= $scope.update.length){
                 switch(name){
                       case "description":
                           $scope.insert[index-$scope.update.length].description =data;
                             break;
                       case "name":
                           $scope.insert[index-$scope.update.length].name =data;
                             break;
                       case "url":
                           $scope.insert[index-$scope.update.length].url =data;
                             break;
                       case "state":
                           $scope.insert[index-$scope.update.length].state =data;
                             break;
                 }
            }else{
                switch(name){
                      case "description":
                           $scope.update[index].description =data;
                             break;
                       case "name":
                           $scope.update[index].name =data;
                             break;
                       case "url":
                           $scope.update[index].url =data;
                             break;
                       case "state":
                           $scope.update[index].state =data;
                             break;
                 }
            }
        };

        $scope.deleteResource = function(index){
            if (index > $scope.resources.length){
                appBase.bubMsg("删除成功");
                $scope.listResource();

            }{
                appBase.doDelete("resources/"+$scope.resources[index].id,null,function(ret){
                    appBase.bubMsg("删除成功");
                    $scope.listResource();
                })
            }

        };

    }

})();
