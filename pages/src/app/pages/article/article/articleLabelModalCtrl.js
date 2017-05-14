/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.article')
		.controller('articleLabelModalCtrl', articleLabelModalCtrl);

	/** @ngInject */
	function articleLabelModalCtrl($scope,$state,columnId,$uibModalInstance,$filter,appBase) {

        $scope.perPage = 20;
		$scope.page = 1;
		$scope.listItem = function(searchValue){
//			var param = "page="+$scope.page+"&perPage="+$scope.perPage+"&searchValue="+searchValue+"&id="+columnId;
			appBase.doGet("articleLabelByLevel?article="+columnId,null,function(response){
				if(response.data != null){
					$scope.labels = response.data;
				}
			})
		}
		$scope.searchValue={value:''};

		$scope.listItem();

		$scope.search = function () {
			$scope.listItem();
		}

        $scope.addLabel = function(id){
            var param = "addable=1&article="+columnId+"&label="+id;
            appBase.doPut("article/label?"+param,null,function(response){
				appBase.bubMsg("操作成功");
				$scope.listItem();
			})
        }

	}

})();