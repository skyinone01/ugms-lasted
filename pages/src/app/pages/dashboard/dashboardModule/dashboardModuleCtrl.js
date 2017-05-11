/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('DashboardModuleCtrl', DashboardModuleCtrl);

	/** @ngInject */
	function DashboardModuleCtrl(baConfig, layoutPaths,appBase,$scope,$rootScope) {
		var layoutColors = baConfig.colors;
		var datas = [];
		$scope.selectPv = true;
		appBase.doGet('statistic/module', null, function (response) {
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
			
			getData(1,"2016",event.item.category);
		}
		
		$scope.pvSelectDetail = pvSelectDetail;
		
		function pvSelectDetail(type,$event){
			//初始化时
			var selectedYear = $event.selectedYear;
			if(typeof(selectedYear) == "undefined")
				selectedYear = "";
			var selectedMonth = $event.selectedMonth;
			if(typeof(selectedMonth) == "undefined")
				selectedMonth = "";
			var selectedDay = $event.selectedDay;
			if(typeof(selectedDay) == "undefined")
				selectedDay = "";
			var getPath = 'statistic/pv-count';
			var name = "";
			if(type == 1)
				name = "月";
			if(type == 2)
				name = "日";
			if(type == 3)
				name = "时";
//			var param={"type":type,"time":data,"moduleName":$scope.moduleName};
			var param={"type":type,"selectedYear":selectedYear,"selectedMonth":selectedMonth,"selectedDay":selectedDay,"moduleName":$scope.moduleName};
			var datas=[];
			$scope.selectPv = false;
			appBase.doGet(getPath,param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type + name,
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
		
		$scope.pvBacks = pvBacks;
		
		function pvBacks(){
			$scope.isDashoardPvTypeShow = true;
			//
			$scope.isDashoardPvShow = false;
			//显示年月日
			$rootScope.isPvBackShow = false;
			DashboardModuleCtrl(baConfig, layoutPaths,appBase,$scope,$rootScope);
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
	}
	
	

	
	
})();