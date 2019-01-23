/**
 * @author roy
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.testPaper.testPaper')
			.controller('TestPaperPageCtrl', TestPaperPageCtrl);

	/** @ngInject */
	function TestPaperPageCtrl($scope,$uibModal, baProgressModal, $filter,appBase) {

		$scope.perPage = 20;
		$scope.page = 1;
		$scope.listItem = function(searchValue){
			var param = "page="+$scope.page+"&perPage="+$scope.perPage+"&searchValue="+searchValue;
			appBase.doGet("articleColumn?"+param,null,function(response){
				if(response.data != null){
					$scope.items = response.data.items;
					$scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
				}
			})
		}
		$scope.searchValue={value:""};
		$scope.listItem($scope.searchValue.value);

		$scope.search = function () {
			$scope.listItem($scope.searchValue.value);
		}

		$scope.showButton = function(index,name){
			if(name == 'edit'){
				var s = $scope.items[index].status;
				if(s==4){
					return false;
				}
				return true;
			}
			if(name == 'request'){
				var s = $scope.items[index].status;
				if(s==0 ){
					return true;
				}
				return false;
			}
			if(name == 'cancel'){
				var s = $scope.items[index].status;
				if(s==1 ){
					return true;
				}
				return false;
			}
			if(name == 'apply'){
				var s = $scope.items[index].status;
				if(s==1 ){
					return true;
				}
				return false;
			}
			if(name == 'release'){
				var s = $scope.items[index].status;
				if(s==2 ){
					return true;
				}
				return false;
			}
			if(name == 'up'){
				var s = $scope.items[index].status;
				if(s==5 ){
					return true;
				}
				return false;
			}
			if(name == 'down'){
				var s = $scope.items[index].status;
				if(s==4 ){
					return true;
				}
				return false;
			}
			if(name == 'delete'){
				var s = $scope.items[index].status;
				if(s==0 || s ==5 ){
					return true;
				}
				return false;
			}
			if(name == 'copy'){
				var s = $scope.items[index].status;
				if(s==5 ){
					return true;
				}
				return false;
			}

			return true;
		}

		$scope.deleteOne = function(id){
			var result = confirm('确认删除！');
			if(!result){
				return;
			}
			appBase.doDelete("articleColumn/"+id,null,function(res){
				appBase.bubMsg("删除成功");
				$scope.listItem();
			});
		}

		//op 1 新增 2详情 3编辑 4 审核
		$scope.open = function (page, size, id,opstr) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'articleModalCtrl',
				resolve: {
					modelId: id,
					op: opstr,
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;

		$scope.statusEmu = ["草稿","待审核","审批通过","审批未通过","已发布","已作废"];
		$scope.showStatus = function(status){
			return $scope.statusEmu[status];
		}

		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.showButtonName = function(index){
			switch($scope.items[index].status){
				case 0:
					return "提交审核";
				case 1:
					return "审核";
				case 2:
					return "发布";
				case 3:
					return "审核";
				case 4:
					return "停用";
				case 5:
					return "启用";
			}
		}
		//审核 1 发布 2 启用 3 停用 4
		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.operation = function(op,id){
			if(op == 10){
				$scope.open('app/pages/article/article/articleModal.html', 'lg',id,4);
				return;
			}
			appBase.doPut("articleColumn/"+id+"?op="+op,null,function(response){
				appBase.bubMsg("操作成功");
				$scope.listItem();
			});
		}


		$scope.upAble = function(page){
			if(page == 1){
				return true;
			}
			return false;
		}

		$scope.nextAble = function(page){
			if ($scope.totalPage == 0){
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
