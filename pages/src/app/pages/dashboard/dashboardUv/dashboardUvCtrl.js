/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('dashboardUvCtrl', dashboardUvCtrl);

	/** @ngInject */
	function dashboardUvCtrl(baConfig, layoutPaths,appBase,$scope,$rootScope) {
		var layoutColors = baConfig.colors;
		var datas = [];

		$scope.uvSelectDetail = uvSelectDetail;
		function uvSelectDetail(type,$event){
			//初始化时
			var selectedUvYear = $event.selectedUvYear;
			if(typeof(selectedUvYear) == "undefined")
				selectedUvYear = "";
			var selectedUvMonth = $event.selectedUvMonth;
			if(typeof(selectedUvMonth) == "undefined")
				selectedUvMonth = "";
			var selectedUvDay = $event.selectedUvDay;
			if(typeof(selectedUvDay) == "undefined")
				selectedUvDay = "";
			if(type==1){
				$scope.selectedNewMonth = "请选择";
				$scope.selectedNewDay = "请选择";
				selectedUvMonth = "";
				selectedUvDay = "";
			}else if(type==2){
				$scope.selectedNewDay = "请选择";
				selectedUvDay = "";
			}
			getUvData(type,selectedUvYear,selectedUvMonth,selectedUvDay);
		}

		getUvData(1,"","","");

		function getUvData(type,selectedYear,selectedMonth,selectedDay) {
			var getPath = 'statistic/count-year';
			var name = "";
			if (type == 1)
				getPath = 'statistic/count-year';
			if (type == 2)
				getPath = 'statistic/count-month';
			if (type == 3)
				getPath = 'statistic/count-day';
			var param = {
				"type": type,
				"selectYear": selectedYear,
				"selectMonth": selectedMonth,
				"selectDay": selectedDay
			};
			var datas = [];
			appBase.doGet(getPath, param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type,
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barUvChart', {
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