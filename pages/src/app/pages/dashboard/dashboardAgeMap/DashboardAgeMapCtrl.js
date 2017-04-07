/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('DashboardAgeMapCtrl', DashboardAgeMapCtrl);

	/** @ngInject */
	function DashboardAgeMapCtrl(baConfig, layoutPaths,$scope,appBase) {
		var layoutColors = baConfig.colors;
		var datas;
		var param={startDate:"",endDate:""}
		appBase.doGet('statistic/age', param,function (response) {
			console.log(response.data);
			datas = [
				{
					country: '30岁以下',
					visits: response.data[0],
					color: layoutColors.primary
				},
				{
					country: '30-40岁',
					visits: response.data[1],
					color: layoutColors.danger

				},
				{
					country: '40-50岁',
					visits: response.data[2],
					color: layoutColors.info
				},
				{
					country: '50-60岁',
					visits: response.data[3],
					color: layoutColors.success
				},
				{
					country: '60-70岁',
					visits: response.data[4],
					color: layoutColors.warning
				},
				{
					country: '70-80岁',
					visits: response.data[5],
					color: layoutColors.primaryLight
				},
				{
					country: '80岁以上',
					visits: response.data[6],
					color: layoutColors.danger
				}
			];
			var map = AmCharts.makeChart('barChart', {
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

})();