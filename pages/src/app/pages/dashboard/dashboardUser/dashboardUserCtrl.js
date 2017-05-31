/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('dashboardUserCtrl', dashboardUserCtrl);

	/** @ngInject */
	function dashboardUserCtrl(baConfig, layoutPaths,appBase,$scope,$rootScope) {
		var layoutColors = baConfig.colors;

		$scope.newSelectDetail = newSelectDetail;
		function newSelectDetail(type,$event){
			//初始化时
			var selectedNewYear = $event.selectedNewYear;
			if(typeof(selectedNewYear) == "undefined")
				selectedNewYear = "";
			var selectedNewMonth = $event.selectedNewMonth;
			if(typeof(selectedNewMonth) == "undefined")
				selectedNewMonth = "";
			var selectedNewDay = $event.selectedNewDay;
			if(typeof(selectedNewDay) == "undefined")
				selectedNewDay = "";
			if(type==1){
				$scope.selectedNewMonth = "请选择";
				$scope.selectedNewDay = "请选择";
				selectedNewMonth = "";
				selectedNewDay = "";
			}else if(type==2){
				$scope.selectedNewDay = "请选择";
				selectedNewDay = "";
			}
			getNewData(type,selectedNewYear,selectedNewMonth,selectedNewDay);
		}

		getNewData(1,"","","");

		function getNewData(type,selectedYear,selectedMonth,selectedDay) {
			var getPath = '/statistic/uv-count';

			var param={"type":type,"selectedYear":selectedYear,"selectedMonth":selectedMonth,"selectedDay":selectedDay};
			var datas=[];
			appBase.doGet(getPath, param, function (response) {
				response.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type,
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barUserChart', {
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