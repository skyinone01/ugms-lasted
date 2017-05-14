/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.column')
		.controller('columnArticleModalCtrl', columnArticleModalCtrl);

	/** @ngInject */
	function columnArticleModalCtrl($scope,$state, articleName,columnId,title,op,$uibModalInstance,$filter,appBase) {

        $scope.perPage = 20;
		$scope.page = 1;
		$scope.listItem = function(searchValue){
			var param = "page="+$scope.page+"&perPage="+$scope.perPage+"&searchValue="+searchValue+"&id="+columnId;
			appBase.doGet("article4Column?"+param,null,function(response){
				if(response.data != null){
					$scope.items = response.data.items;
					$scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
				}
			})
		}
		if(articleName != null){
		    $scope.searchValue={value:articleName};
		}else{
		    $scope.searchValue={value:''};
		}

		$scope.listItem($scope.searchValue.value);

		$scope.search = function () {
			$scope.listItem($scope.searchValue.value);
		}


        $scope.addArticle = function(id){
            var param = "addable=1&column="+columnId+"&article="+id;
            appBase.doPut("articleColumn?"+param,null,function(response){
				appBase.bubMsg("操作成功");
				$scope.listItem($scope.searchValue.value);
			})
        }
        $scope.backTo = function(){
            $state.go("article.column/"+columnId+"/"+op, {}, { reload: true });
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