/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userInstructions.instruction')
		.controller('InstructionsCtrl', InstructionsCtrl);

//	$scope, $uibModalInstance, modelId, $http,fileReader, $filter, appBase,$state
//	$scope, $uibModalInstance, modelId, $http,fileReader, $filter,appCommon
	/** @ngInject */
	function InstructionsCtrl($scope, $http, $uibModal, baProgressModal,appCommon,appBase,$state) {
		//debugger;
		var localPath = appCommon.autoCompleteUrl();//'http://localhost:8080';
		var apiPath = localPath.substring(0, localPath.lastIndexOf('/'));
		$scope.standardItem = {};

		$scope.pageNum = 1;

		$scope.searchNameValue = '';

		/*$scope.selectStatus = (function (pageNum, value, name) {
			var url = apiPath+'/instructions/'
				+ (pageNum == 1 ? 0 : pageNum) * 15
				+ '/15?type=instructions';
			if (value != 99 && value != undefined) {
				url = url + '&status=' + value;
			}
			if (name != undefined && name != '' && value != undefined) {
				url = url + '&content=' + name;
			}
			$http.get(url).then(function (response) {
				$scope.smartTableData = [];
				response.data.forEach(function (value) {
					$scope.smartTableData.push({
						id: value.id,
						serialnumber: value.serialnumber,
						title: value.title,
						imgUrl: apiPath+"/"+value.imgUrl,
						status: value.status
					})
				});
			});
		});*/

		
		
		$scope.open = function (page, size, id,opstr) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'instructionsModalCtrl',
				resolve: {
					modelId: id,
					op: opstr,
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;

		$scope.delete = (function (id) {
			var result = confirm('确认删除！');
			if (result) {
				$http.delete(apiPath+'/instructions/' + id)
					.then(function (response) {
						if (response.data.error == 'succ') {
							alert("删除成功");
//							$scope.selectStatus($scope.pageNum, '', '');
							$scope.listItem();
						}
						else alert('失败');
					});
			}
		});

		
		
		
		$scope.perPage = 20;
        $scope.page = 1;
        $scope.listItem = function(){
			var param = "page="+$scope.page+"&perPage="+$scope.perPage;
			appBase.doGet("instructions?"+param,null,function(response){
        		     if(response.data != null){
        		         $scope.smartTableData = response.data.items;
        		         $scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
        		     }
        		})
        }
		$scope.listItem();
		
		
		

//		$.get(apiPath+'/instructions/0/15/?type=instructions', function (data) {
//			$scope.smartTableData = [];
//			console.log(data);
//			data.forEach(function (value, index, array) {
//				$scope.smartTableData.push({
//					id: value.id,
//					serialnumber: value.serialnumber,
//					title: value.title,
//					imgUrl: apiPath+"/"+value.imgUrl
//				})
//			});
//		});

		/*$scope.btnNext = (function (pageNum) {
			var offset = 15 * pageNum;
			$http.get(apiPath+'/instructions/' + offset + '/15?type=instructions').then(function (response) {
				if (response.data.lastPage >= pageNum) {
					$scope.smartTableData = [];
				}
				else {
					return true;
				}
				response.data.list.forEach(function (value) {

					$scope.smartTableData.push({
						id: value.id,
						serialnumber: value.serialnumber,
						title: value.title,
						imgUrl: apiPath+"/"+value.imgUrl
					})
				});
				$scope.pageNum = pageNum + 1;
			});
		});*/

		/*$scope.btnUp = (function (pageNum) {
			var offset = 15 * (pageNum - 1);
			$http.get(apiPath+'/instructions/' + offset + '/15?type=instructions').then(function (response) {
				if (response.data.hasNextPage) {
					if (offset < 0) {
						return true;
					}
					$scope.smartTableData = [];
				}
				else {
					$scope.smartTableData = [];
				}
				response.data.forEach(function (value) {
					$scope.smartTableData.push({
						id: value.id,
						serialnumber: value.serialnumber,
						title: value.title,
						imgUrl: apiPath+"/"+value.imgUrl
					})
				});
				$scope.pageNum = pageNum == 0 ? 1 : pageNum;
			});
		});*/
		
		
		$scope.upAble = function(page){
            if(page == 1){
                return true;
            }
            return false;
        }

        $scope.nextAble = function(page){
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
