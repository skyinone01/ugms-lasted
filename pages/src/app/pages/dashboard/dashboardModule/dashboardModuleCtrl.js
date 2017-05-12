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
//			alert(event.item.category);
			$scope.isDashoardPvTypeShow = false;
			$scope.isDashoardPvShow = true;
			$scope.moduleName = event.item.category;
			$rootScope.isPvBackShow = true;
			$scope.isPvYear = true;
			$scope.isPvMonth = true;
			$scope.isPvDay = true;
			$scope.pvBack = true;
			$scope.selectPvYear = false;
			$scope.selectPvYearLab = false;
			$scope.selectPvMonth = false;
			$scope.selectPvMonthLab = false;
			$scope.selectPvDay = false;
			$scope.selectPvDayLab = false;
			$scope.selectPvBack = false;
			$scope.selectedYear = "请选择";
			$scope.selectedMonth = "请选择";
			$scope.selectedDay = "请选择";
			var oDate = new Date(); //实例一个时间对象；
			var year = oDate.getFullYear();   //获取系统的年；
			getData(1,year,event.item.category);
			$scope.selectPv = false;
		}
		
		$rootScope.pvDetail = pvDetail;
		function pvDetail(type,$event){
			$scope.selectPvYear = true;
			$scope.selectPvYearLab = true;
			$scope.selectPvMonth = true;
			$scope.selectPvMonthLab = true;
			$scope.selectPvDay = true;
			$scope.selectPvDayLab = true;
			$scope.selectPvBack = true;
			$scope.selectPv = true;
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