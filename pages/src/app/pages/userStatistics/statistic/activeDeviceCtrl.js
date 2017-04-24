/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userStatistics.statistic')
		.controller('ActiveDeviceCtrl', ActiveDeviceCtrl);

	/** @ngInject */
	function ActiveDeviceCtrl($scope,$uibModal,$http, baProgressModal, $filter,appBase,appCommon) {
		$scope.perPage = 20;
        $scope.page = 1;
        $scope.listItem = function(){
			var param = "page="+$scope.page+"&perPage="+$scope.perPage;
			appBase.doGet("statistic/activeDevice?"+param,null,function(response){
        		     if(response.data != null){
        		         $scope.items = response.data.items;
        		         $scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
        		     }
        		})
        }
		$scope.listItem();
		
		
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