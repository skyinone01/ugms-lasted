/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.discovery')
		.controller('typeModalCtrl', typeModalCtrl);

	/** @ngInject */
	function typeModalCtrl($scope, $uibModalInstance,$filter,appBase) {

		$scope.types = [];
		$scope.role = [];
		$scope.update = [];

		$scope.listType=function(){
			appBase.doGet("type",null,function(ret){
				$scope.types = ret.data;
				$scope.update = ret.data.slice(0);
			})
		}
		$scope.listType();


		$scope.removeType = function(index) {
			$scope.types.splice(index, 1);
		};
		//**user save
		$scope.insert = [];
		$scope.addType = function() {
			$scope.inserted = {
				id: 0,
				name: '',
				orderId:''
			};
			$scope.types.push($scope.inserted);
			$scope.insert.push($scope.inserted);
		};
		$scope.saveType = function(index){
			var data;
			if(index >= $scope.update.length){
				data = $scope.insert[index-$scope.update.length];
			}else{
				data = $scope.update[index];
			}
			if (data.name == "" || data.name == null){
				appBase.bubMsg("名称不能为空");
				return;
			}
			if (data.orderId == "" || data.orderId == null){
				appBase.bubMsg("排序不能为空");
				return;
			}
			appBase.doPut("type",data,function(ret){
				appBase.bubMsg("保存成功");
				$scope.listType();
			})
		};

		$scope.valueChange = function(parent,index){
			//var  name = $(event.target).parent().parent().prev().attr("e-name");
			var  name = parent.$editable.name;
			var  data = parent.$data;
			if(index >= $scope.update.length){
				switch(name){
					case "name":
						$scope.insert[index-$scope.update.length].name =data;
						break;
					case "orderId":
						$scope.insert[index-$scope.update.length].orderId =data;
						break;
				}
			}else{
				switch(name){
					case "name":
						$scope.update[index].name =data;
						break;
					case "orderId":
						$scope.update[index].orderId =data;
						break;
				}
			}
		};

		$scope.cancelSave = function(index){

		};
		$scope.deleteType = function(index){
			appBase.doDelete("type/"+$scope.types[index].id,null,function(ret){
				appBase.bubMsg("删除成功");
				$scope.listType();
				//$scope.removeType(index);
			})
		};

	}

})();