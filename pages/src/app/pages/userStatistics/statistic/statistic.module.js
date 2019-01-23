/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userStatistics.statistic', [])
		.config(routeConfig);

	/** @ngInject */
	function routeConfig($stateProvider, $urlRouterProvider) {
		
		$stateProvider.state('statistics.statistic', {
				url: '/statistics/statistic',
				title: '用户统计详情',
				templateUrl: 'app/pages/userStatistics/statistic/statistic.html',
				controller: 'StatisticCtrl',
				sidebarMeta: {
	                order: 200,
	            }
			});
	}

})();
