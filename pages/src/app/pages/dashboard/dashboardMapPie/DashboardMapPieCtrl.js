/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.dashboard')
		.controller('DashboardMapPieCtrl', DashboardMapPieCtrl);

	/** @ngInject */
	function DashboardMapPieCtrl(baConfig, layoutPaths,$scope,appBase) {
		var layoutColors = baConfig.colors;
		var datas;
		appBase.doGet('statistic/age', null, function (response) {
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
			AmCharts.makeChart('barPieChart', {
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
				valueField: 'visits',
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