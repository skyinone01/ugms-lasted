/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userStatistics.statistic')
		.controller('StatisticCtrl', StatisticCtrl);

	/** @ngInject */
	function StatisticCtrl($scope,$uibModal, $http,$rootScope,baProgressModal,appBase,appCommon,baConfig, layoutPaths) {
		var layoutColors = baConfig.colors;
		var localPath = appCommon.autoCompleteUrl();//'http://localhost:8080';
		var apiPath = localPath.substring(0, localPath.lastIndexOf('/'));
		var agetype;
		var pvtype;
		$scope.DateType="2";
		$scope.year = ["2013", "2014", "2015","2016","2017","2018","2019"];
		$scope.month = ["1", "2", "3","4","5","6","7","8","9","10","11","12"];
		$scope.day = ["1", "2", "3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"];
		
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
					type: value.type,
					count:value.count
				})
			});
		});

		appBase.doGet('statistic/active-module', null, function (response) {
			$scope.moduleTable = [];
			response.data.forEach(function (value, index, array) {
				$scope.moduleTable.push({
					type: value.type,
					count:value.count
				})
			});
		});

		appBase.doGet('statistic/active-device', null, function (response) {
			$scope.deviceTable = [];
			response.data.forEach(function (value, index, array) {
				$scope.deviceTable.push({
					type: value.type.length > 15 ? value.type.substr(0, 20) + '...' : value.type,
					count:value.count
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
		};
		
		//活跃用户
		$scope.activeUser = function(){
			$uibModal.open({
				animation: true,
				templateUrl: 'app/pages/userStatistics/statistic/activeUser.html',
				size: 'lg',
				controller: 'ActiveUserCtrl'
			});
		}
		$scope.openProgressDialog = baProgressModal.open;
		
		//活跃机型
		$scope.activeDevice = function(){
			$uibModal.open({
				animation: true,
				templateUrl: 'app/pages/userStatistics/statistic/activeDevice.html',
				size: 'lg',
				controller: 'ActiveDeviceCtrl'
			});
		}
		$scope.openProgressDialog = baProgressModal.open;
		
		//活跃模块
		$scope.activeModule = function(){
			$uibModal.open({
				animation: true,
				templateUrl: 'app/pages/userStatistics/statistic/activeModule.html',
				size: 'lg',
				controller: 'ActiveModuleCtrl'
			});
		}
		$scope.openProgressDialog = baProgressModal.open;
		
		
		$scope.export = function () {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var param = "&startDate="+startDate+"&endDate="+endDate;
			
			window.location.href=apiPath+'/statistic/exportComprehensiveUserStats?'+param;
		}
		
		$scope.exportUserStats = function () {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var userName = $("#searchNameValue").val();
			
			var param = "";
			if(userName!=""&& userName!=undefined){
				param = param + "&userName="+userName;
			}else{
				param = param + "&userName="+null;
			}
			
			/*if(startDate!=""&& startDate!=undefined){
				param = param + "&startDate="+startDate;
			}else{
				param = param + "&startDate="+null;
			}
			if(endDate!=""&& endDate!=undefined){
				param = param + "&endDate="+endDate;
			}else{
				param = param + "&endDate="+null;
			}*/
			
			window.location.href=apiPath+'/basic/exportUserStats?'+param;
			
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
			var name = "";
			var datas = [];
			if(type == 1)
				name = "1";
			if(type == 2)
				name = "2";
			if(type == 3)
				name = "3";
//			$scope.selectPv = true;
//			$scope.isDashoardPvShow = true;
			var param = {"type":name};
			var datas=[];
			$scope.selectPv = false;
			appBase.doGet("statistic/selectPvModule",param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type,
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barModuleChart', {
					type: 'serial',
					theme: 'blur',
					color: layoutColors.defaultText,
					dataProvider: datas,
					startDuration: 1,
					graphs: [
						{
							balloonText: '<b>[[category]]: [[value]]</b>',
							fillColorsField: 'color',
							fillAlphas: 0.7,
							lineAlpha: 0.2,
							type: 'column',
							valueField: 'visits'
						}
					],
					chartCursor: {
						categoryBalloonEnabled: false,
						cursorAlpha: 0,
						zoomable: false
					},
					categoryField: 'country',
					categoryAxis: {
						gridPosition: 'start',
						labelRotation: 45,
						gridAlpha: 0.5,
						gridColor: layoutColors.border,
					},
					export: {
						enabled: true
					},
					creditsPosition: 'top-right',
					pathToImages: layoutPaths.images.amChart
				});
				map.addListener("clickGraphItem", handleClick);
			});
			
		}
		
		//柱状图点击事件
		function handleClick(event){
			$scope.isDashoardPvTypeShow = false;
			$scope.isDashoardPvShow = true;
			$scope.moduleName = event.item.category;
			$rootScope.isPvBackShow = true;
//			$scope.isPvYear = true;
//			$scope.isPvMonth = true;
//			$scope.isPvDay = true;
			$scope.pvBack = true;
			alert(event.item.category);
			getData(1,"2016",event.item.category);
		}
		
		//初始化
		function getData(type,selectedYear,moduleName) {
			var getPath = 'statistic/pv-count';
//			var param={"type":type,"time":data,"moduleName":$scope.moduleName};
			var param={"type":type,"selectedYear":selectedYear,"selectedMonth":"","selectedDay":"","moduleName":moduleName};
			var datas=[];
			$scope.selectPv = false;
			appBase.doGet(getPath,param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type + "月",
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barPvChart', {
					type: 'serial',
					theme: 'blur',
					color: layoutColors.defaultText,
					dataProvider: datas,
					startDuration: 1,
					graphs: [
						{
							balloonText: '<b>[[category]]: [[value]]</b>',
							fillColorsField: 'color',
							fillAlphas: 0.7,
							lineAlpha: 0.2,
							type: 'column',
							valueField: 'visits'
						}
					],
					chartCursor: {
						categoryBalloonEnabled: false,
						cursorAlpha: 0,
						zoomable: false
					},
					categoryField: 'country',
					categoryAxis: {
						gridPosition: 'start',
						labelRotation: 45,
						gridAlpha: 0.5,
						gridColor: layoutColors.border,
					},
					export: {
						enabled: true
					},
					creditsPosition: 'top-right',
					pathToImages: layoutPaths.images.amChart
				});
				

			});
		}
		
		//device 机型
		$scope.isDashoardDeviceShow=false;
		$scope.isDashoardDeviceTypeShow=true;
		$rootScope.isDeviceBackShow=false;
		$rootScope.isDeviceBackHidden=true;
		$scope.deviceDetail = deviceDetail;
		$scope.deviceBack = deviceBack;
		function deviceBack($event) {
			$scope.DeviceValue=0;
			$rootScope.isDeviceBackShow=false;
			$rootScope.isDeviceBackHidden=true;
		}
		
		function deviceDetail(type,$event) {
			$rootScope.deviceType=type;
			if(type==1) {
				if ($scope.isDeviceYear == true) {
					$scope.isDeviceYear = false;
				}else {
					$scope.isDeviceYear = true;
					$scope.isDeviceMonth = false;
					$scope.isDeviceDay = false;
				}
			}else if(type==2){
				if ($scope.isDeviceMonth == true) {
					$scope.isDeviceMonth = false;
				}else {
					$scope.isDeviceYear = false;
					$scope.isDeviceMonth = true;
					$scope.isDeviceDay = false;
				}
			}else if(type==3){
				if ($scope.isDeviceDay == true) {
					$scope.isDeviceDay = false;
				}else {
					$scope.isDeviceYear = false;
					$scope.isDeviceMonth = false;
					$scope.isDeviceDay = true;
				}
			}
			if($scope.isDeviceYear||$scope.isDeviceMonth||$scope.isDeviceDay)	{
				$scope.isDashoardDeviceShow=true;
				$scope.isDashoardDeviceTypeShow=false;
			}else {
				$scope.isDashoardDeviceShow=false;
				$scope.isDashoardDeviceTypeShow=true;
			}
			$scope.DeviceValue=type;
		}
	}

})();
