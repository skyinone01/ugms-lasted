/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('dashboardUvCtrl', dashboardUvCtrl);

	/** @ngInject */
	function dashboardUvCtrl(baConfig, layoutPaths,appBase) {
		var layoutColors = baConfig.colors;
		var datas = [];
//		$.get('http://localhost:8080/statistic/uv', function (data) {
		appBase.doGet('/statistic/uv', null, function (response) {
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
})();