/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userStatistics.statistic')
		.controller('StatisticCtrl', StatisticCtrl);

	/** @ngInject */
	function StatisticCtrl($scope, $http,$rootScope,appBase) {
		var agetype;
		var pvtype;
		$scope.DateType="2";
		/*$scope.export = (function () {
			$http.get('http://localhost:8080/exports/').then(function (response) {
				function downloadFile(fileName, urlData) {
					var aLink = document.createElement('a');
					aLink.download = fileName;
					aLink.href = urlData;

					var event = new MouseEvent('click');
					aLink.dispatchEvent(event);
				}

				downloadFile('统计.csv', 'data:text/csv;charset=UTF-8,' + encodeURIComponent(response.data));
			});
		});*/

		appBase.doGet('statistic/active-user', null, function (response) {
			$scope.userTable = [];
			response.data.forEach(function (value, index, array) {
				$scope.userTable.push({
					type: value.type
				})
			});
		});

		appBase.doGet('statistic/active-module', null, function (response) {
			$scope.moduleTable = [];
			response.data.forEach(function (value, index, array) {
				$scope.moduleTable.push({
					type: value.type
				})
			});
		});

		appBase.doGet('statistic/active-device', null, function (response) {
			$scope.deviceTable = [];
			response.data.forEach(function (value, index, array) {
				$scope.deviceTable.push({
					type: value.type.length > 15 ? value.type.substr(0, 30) + '...' : value.type
				})
			});
		});

		$scope.open = open;
		$scope.opened = false;
		$scope.formats = ['yyyyMMdd', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		$scope.format = $scope.formats[0];
		$scope.options = {
			showWeeks: false
		};
		//$scope.startdate = new Date();
		//$scope.enddate = new Date();

		function open() {
			$scope.opened = true;
		}
		
		//年龄
		$scope.isDashoardShow=false;
		$scope.isDashoardTypeShow=true;
		$rootScope.isAgeBackShow=false;
		$rootScope.isAgeBackHidden=true;
		$scope.ageDetail = ageDetail;
		$scope.ageBack = ageBack;
		function ageBack($event) {
			$scope.TypeValue=0;
			$rootScope.isAgeBackShow=false;
			$rootScope.isAgeBackHidden=true;
			//alert($scope.TypeValue);
			//alert($rootScope.ageType);
			//$scope.TypeValue=$rootScope.ageType;
			//$scope.TypeValue=agetype;
			//alert($scope.TypeValue);
		}

		function ageDetail(type,$event) {
			//agetype=type;
			$rootScope.ageType=type;
			if(type==1) {
				if ($scope.isAgeYear == true) {
					$scope.isAgeYear = false;
				}else {
					$scope.isAgeYear = true;
					$scope.isAgeMonth = false;
					$scope.isAgeDay = false;
				}
			}else if(type==2){
				if ($scope.isAgeMonth == true) {
					$scope.isAgeMonth = false;
				}else {
					$scope.isAgeYear = false;
					$scope.isAgeMonth = true;
					$scope.isAgeDay = false;
				}
			}else if(type==3){
				if ($scope.isAgeDay == true) {
					$scope.isAgeDay = false;
				}else {
					$scope.isAgeYear = false;
					$scope.isAgeMonth = false;
					$scope.isAgeDay = true;
				}
			}
			if($scope.isAgeYear||$scope.isAgeMonth||$scope.isAgeDay)	{
				$scope.isDashoardShow=true;
				$scope.isDashoardTypeShow=false;
			}else {
				$scope.isDashoardShow=false;
				$scope.isDashoardTypeShow=true;
			}
			$scope.TypeValue=type;
		}
		
		//PV(模块点击统计)
		$scope.isDashoardPvShow=false;
		$scope.isDashoardPvTypeShow=true;
		$rootScope.isPvBackShow=false;
		$rootScope.isPvBackHidden=true;
		$scope.pvDetail = pvDetail;
		$scope.pvBack = pvBack;
		function pvBack($event) {
			$scope.PvValue=0;
			$rootScope.isPvBackShow=false;
			$rootScope.isPvBackHidden=true;
		}
		
		function pvDetail(type,$event) {
			//agetype=type;
			$rootScope.pvType=type;
			if(type==1) {
				if ($scope.isPvYear == true) {
					$scope.isPvYear = false;
				}else {
					$scope.isPvYear = true;
					$scope.isPvMonth = false;
					$scope.isPvDay = false;
				}
			}else if(type==2){
				if ($scope.isPvMonth == true) {
					$scope.isPvMonth = false;
				}else {
					$scope.isPvYear = false;
					$scope.isPvMonth = true;
					$scope.isPvDay = false;
				}
			}else if(type==3){
				if ($scope.isPvDay == true) {
					$scope.isPvDay = false;
				}else {
					$scope.isPvYear = false;
					$scope.isPvMonth = false;
					$scope.isPvDay = true;
				}
			}
			if($scope.isPvYear||$scope.isPvMonth||$scope.isPvDay)	{
				$scope.isDashoardPvShow=true;
				$scope.isDashoardPvTypeShow=false;
			}else {
				$scope.isDashoardPvShow=false;
				$scope.isDashoardPvTypeShow=true;
			}
			$scope.PvValue=type;
		}
	}

})();
