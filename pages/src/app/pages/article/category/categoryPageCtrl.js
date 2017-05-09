/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.category')
		.controller('CategoryPageCtrl', CategoryPageCtrl);

	/** @ngInject */
	function CategoryPageCtrl($scope,appBase) {

		$scope.types = [];
		$scope.update = [];

		$scope.perPage = 30;
		$scope.page = 1;
		$scope.listType = function(){
			appBase.doGet("articleCategory?page="+$scope.page+"&perPage="+$scope.perPage,null,function(response){
				if(response.data != null){
					$scope.types = response.data.items;
					$scope.update = response.data.items.slice(0);
					$scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
				}
			})
		}
		$scope.listType();

		$scope.cancel = function(){
			$scope.listType();
		}

		$scope.removeType = function(index) {
			$scope.types.splice(index, 1);
		};
		//**user save
		$scope.insert = [];
		$scope.addType = function() {
			$scope.inserted = {
				id: 0,
				name: '',
				level:0
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
			if (data.sort == null || data.sort == 0){
				appBase.bubMsg("排序不能为空");
				return;
			}
			appBase.doPut("articleCategory",data,function(ret){
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
					case "sort":
						$scope.insert[index-$scope.update.length].sort =data;
						break;
				}
			}else{
				switch(name){
					case "name":
						$scope.update[index].name =data;
						break;
					case "sort":
						$scope.update[index].sort =data;
						break;
				}
			}
		};

		$scope.deleteType = function(index){
			appBase.doDelete("articleCategory/"+$scope.types[index].id,null,function(ret){
				appBase.bubMsg("删除成功");
				$scope.listType();
				//$scope.removeType(index);
			})
		};


		$scope.upAble = function(page){
			if(page == 1){
				return true;
			}
			return false;
		}

		$scope.nextAble = function(page){
			if($scope.totalPage==0){
				return true;
			}
			if(page == $scope.totalPage){
				return true;
			}
			return false;
		}

		$scope.btnNext = function(){
			$scope.page = $scope.page+1;
			$scope.listItem();
		};

		$scope.btnUp = function () {
			$scope.page = $scope.page-1;
			$scope.listItem();
		};

	}

})();