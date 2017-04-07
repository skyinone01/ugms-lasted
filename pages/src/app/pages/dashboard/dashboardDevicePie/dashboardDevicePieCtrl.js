/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('dashboardDevicePieCtrl', dashboardDevicePieCtrl);

	/** @ngInject */
	function dashboardDevicePieCtrl(baConfig, layoutPaths,appBase) {
		var layoutColors = baConfig.colors;
		var datas = [];
		appBase.doGet('statistic/device', null, function (response) {
			response.data.forEach(function (value, index, array) {
				datas.push({
					country: value.type == 1 ? '苹果' : '安卓',
					litres: value.count
				})
			});
			AmCharts.makeChart('barDevicePieChart', {
				type: 'pie',
				startDuration: 0,
				theme: 'blur',
				addClassNames: true,
				color: layoutColors.defaultText,
				labelTickColor: layoutColors.borderDark,
				legend: {
					position: 'right',
					marginRight: 100,
					autoMargins: false,
				},
				innerRadius: '40%',
				defs: {
					filter: [
						{
							id: 'shadow',
							width: '200%',
							height: '200%',
							feOffset: {
								result: 'offOut',
								in: 'SourceAlpha',
								dx: 0,
								dy: 0
							},
							feGaussianBlur: {
								result: 'blurOut',
								in: 'offOut',
								stdDeviation: 5
							},
							feBlend: {
								in: 'SourceGraphic',
								in2: 'blurOut',
								mode: 'normal'
							}
						}
					]
				},
				dataProvider: datas,
				valueField: 'litres',
				titleField: 'country',
				export: {
					enabled: true
				},
				creditsPosition: 'bottom-left',

				autoMargins: false,
				marginTop: 10,
				alpha: 0.8,
				marginBottom: 0,
				marginLeft: 0,
				marginRight: 0,
				pullOutRadius: 0,
				pathToImages: layoutPaths.images.amChart,
				responsive: {
					enabled: true,
					rules: [
						// at 900px wide, we hide legend
						{
							maxWidth: 900,
							overrides: {
								legend: {
									enabled: false
								}
							}
						},

						// at 200 px we hide value axis labels altogether
						{
							maxWidth: 200,
							overrides: {
								valueAxes: {
									labelsEnabled: false
								},
								marginTop: 30,
								marginBottom: 30,
								marginLeft: 30,
								marginRight: 30
							}
						}
					]
				}
			});

		});

	}
})();