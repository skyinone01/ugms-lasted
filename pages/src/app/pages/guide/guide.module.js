/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.guide', [])
		.config(routeConfig);

	/** @ngInject */
	function routeConfig($stateProvider) {
		$stateProvider
			.state('guide', {
				url: '/guide',
				title: '欢迎页',
				templateUrl: 'app/pages/guide/guide.html',
				controller: 'guideCtrl',
			});
	}

})();
