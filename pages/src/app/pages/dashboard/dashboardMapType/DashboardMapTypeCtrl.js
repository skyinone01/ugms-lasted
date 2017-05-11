/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
	'use strict';
	angular.module('BlurAdmin.pages.dashboard')
		.controller('DashboardMapTypeCtrl', DashboardMapTypeCtrl);

	/** @ngInject */
	function DashboardMapTypeCtrl(baConfig, layoutPaths,$scope,$rootScope,appBase) {
		var layoutColors = baConfig.colors;
		var datas;
		var apiPath = 'http://localhost:8080';
		function getData(bdate,edate) {
			debugger;
			var param={startDate:bdate,endDate:edate};
			appBase.doGet('statistic/age',param, function (response) {
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
			var map = AmCharts.makeChart('barTypeChart', {
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
		function getDataByType(type,bdate,edate) {
			var getPath='statistic';
			if(type==1){
				getPath=getPath+"/count-year"
			}else if(type==2){
				getPath=getPath+"/count-month"
			}else if(type==3){
				getPath=getPath+"/count-day"
			}else{
				getPath=getPath+"/count-year"
			}
			var param={"startDate":bdate,"endDate":edate};
			datas=[];
			appBase.doGet(getPath,param, function (data) {
				data.data.forEach(function (value, index, array) {
					datas.push({
						country: value.type,
						visits: value.count,
						color: layoutColors.primary
					})
				});
				var map = AmCharts.makeChart('barTypeChart', {
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

		function handleClick(event)
		{
			$rootScope.isAgeBackShow=true;
			$rootScope.isAgeBackHidden=false;
			$scope.$apply();
			var bdate=typeof($rootScope.startDate)!="undefined"?new moment($rootScope.startDate).format('YYYYMMDD'):""
			var edate=typeof($rootScope.endDate)!="undefined"?new moment($rootScope.endDate).format('YYYYMMDD'):""
			//if($scope.TypeValue==3){
			//	if(bdate!="")bdate=bdate+""+event.item.category.substring(0,2);
			//	if(edate!="")edate=edate+""+event.item.category.substring(0,2);
			//}
			if($scope.TypeValue==1) {
				var tmpbdate = event.item.category+""+"01";
				var tmpedate = event.item.category+""+"31";
			}
			if($scope.TypeValue==2) {
				var tmpbdate = event.item.category+""+"000000";
				var tmpedate = event.item.category+""+"235959";
				bdate=bdate+""+"000000";
				edate=edate+""+"235959";
			}
			if($scope.TypeValue==3) {
				var tmpbdate = event.item.category+""+"0000";
				var tmpedate = event.item.category+""+"5959";
				bdate=bdate+""+"000000";
				edate=edate+""+"235959";
		}
			if(bdate<tmpbdate)bdate=tmpbdate;
			if(edate>tmpedate)edate=tmpedate;
			getData(bdate,edate);
			//alert($scope.TypeValue);
			//$scope.TypeValue=2;
			//alert($scope.TypeValue);

		}

		$scope.$watch('TypeValue',function () {
			if($scope.TypeValue!=0) {
				getDataByType($scope.TypeValue, typeof($rootScope.startDate) != "undefined" ? new moment($rootScope.startDate).format('YYYYMMDD') : "", typeof($rootScope.endDate) != "undefined" ? new moment($rootScope.endDate).format('YYYYMMDD') : "");
			}else{
				getDataByType($rootScope.ageType, typeof($rootScope.startDate) != "undefined" ? new moment($rootScope.startDate).format('YYYYMMDD') : "", typeof($rootScope.endDate) != "undefined" ? new moment($rootScope.endDate).format('YYYYMMDD') : "");
			}
		},true);
	}

})();