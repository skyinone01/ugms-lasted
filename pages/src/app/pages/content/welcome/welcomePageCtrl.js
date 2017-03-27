/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.welcome')
        .controller('WelcomePageCtrl', WelcomePageCtrl);

    /** @ngInject */
    function WelcomePageCtrl($scope,$uibModal, baProgressModal, $filter,appBase) {
//
//        $scope.welcomes = [];
//        $scope.listWelcome=function(){
//            appBase.doGet("welcomes",null,function(ret){
//                 $scope.welcomes = ret.data.items;
//            })
//        }
        $scope.open = function(){
                $uibModal.open({
    				animation: true,
    				templateUrl: page,
    				size: size,
    				controller: 'welcomeModalCtrl',
    				resolve: {
    					modelId: id
    				}
    			});
        }

        $scope.pageNum = 1;
		$scope.searchNameValue = '';

		appBase.doGet("welcome","{'page':0,'size':20}",function(response){
		     if(response.data != null){
		         $scope.items = response.data.items;
		     }
		})

		$scope.selectStatus = (function (pageNum, name) {
			var url = 'http://120.26.88.27:8080/youge-backend/showMessage/'
				+ (pageNum == 1 ? 0 : pageNum) * 15
				+ '/15?YGType=1';

			if (name != undefined && name != '') {
				url = url + '&YGTitle=' + name;
			}
			$http.get(url).then(function (response) {
				$scope.smartTableData = [];
				response.data.list.forEach(function (value) {
					$scope.smartTableData.push({
						YGID: value.ygid,
						YGTitle: value.ygtitle,
						YGCreateDate: value.ygcreateDate
					})
				});
			});
		});

		$scope.delete = (function (id) {
			var result = confirm('确认删除！');
			if (result) {
				$http.delete('http://120.26.88.27:8080/youge-backend/showMessage/' + id)
					.then(function (response) {
						if (response.data == 1) {
							alert("删除成功");
							$scope.selectStatus($scope.pageNum, '', '');
						}
						else alert('失败');
					});
			}
		});

		$scope.open = function (page, size, id) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'welcomeModalCtrl',
				resolve: {
					modelId: id
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;



		$scope.btnNext = (function (pageNum) {
			var offset = 15 * pageNum;
			$http.get('http://120.26.88.27:8080/youge-backend/showMessage/' + offset + '/15?YGType=1').then(function (response) {
				if (response.data.lastPage > pageNum) {
					$scope.smartTableData = [];
				}
				else {
					return true;
				}
				response.data.list.forEach(function (value) {
					$scope.smartTableData.push({
						YGID: value.ygid,
						YGTitle: value.ygtitle,
						YGCreateDate: value.ygcreateDate
					})
				});
				$scope.pageNum = pageNum + 1;
			});
		});

		$scope.btnUp = (function (pageNum) {
			var offset = 15 * (pageNum - 1);
			$http.get('http://120.26.88.27:8080/youge-backend/showMessage/' + offset + '/15?YGType=1').then(function (response) {
				if (response.data.hasNextPage) {
					if (offset < 0) {
						return true;
					}
					$scope.smartTableData = [];
				}
				else {
					return true;
				}
				response.data.list.forEach(function (value) {
					$scope.smartTableData.push({
						YGID: value.ygid,
						YGTitle: value.ygtitle,
						YGCreateDate: value.ygcreateDate
					})
				});
				$scope.pageNum = pageNum == 0 ? 1 : pageNum;
			});
		});


    }

})();
