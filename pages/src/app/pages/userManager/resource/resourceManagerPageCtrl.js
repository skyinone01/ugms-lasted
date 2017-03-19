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
            appBase.doGet("resources",null,function(ret){
                 $scope.resources = ret.data.items;
                 $scope.update = ret.data.items;
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
                data = $scope.insert[index];
            }else{
                data = $scope.update[index];
            }
            appBase.doPost("resources",data,function(ret){
                appBase.bubMsg("保存成功");
                $scope.listResource();
            })
        };
        $scope.change = function(obj,data){
            alert(obj);
        };

        $scope.valueChange = function(event,index){
            var key = $(event.target).parent().parent().prev().attr("e-name");
            if(index >= $scope.update.length){
                 switch(key){
                       case "description":
                           $scope.insert[index].description =event.target.value;
                             break;
                       case "name":
                           $scope.insert[index].name =event.target.value;
                             break;
                       case "url":
                           $scope.insert[index].url =event.target.value;
                             break;
                       case "state":
                           $scope.insert[index].state =event.target.value;
                             break;
                 }
            }else{
                switch(key){
                      case "description":
                           $scope.update[index].description =event.target.value;
                             break;
                       case "name":
                           $scope.update[index].name =event.target.value;
                             break;
                       case "url":
                           $scope.update[index].url =event.target.value;
                             break;
                       case "state":
                           $scope.update[index].state =event.target.value;
                             break;
                 }
            }
        };

        $scope.cancelSave = function(index){

        };

        $scope.deleteResource = function(index){
            appBase.doPost("resources/delete",$scope.resources[index],function(ret){
                appBase.bubMsg("删除成功");
                $scope.removeResource(index);
             })
        };

    }

})();
