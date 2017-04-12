/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userInstructions.instruction', [])
		.config(routeConfig);

	/** @ngInject */
	function routeConfig($stateProvider, $urlRouterProvider) {
		
		$stateProvider.state('instructions.instruction', {
				url: '/instruction',
				title: '使用说明管理',
				templateUrl: 'app/pages/userInstructions/instructions/instructions.html',
				controller: 'InstructionsCtrl',
				sidebarMeta: {
	                order: 200,
	            }
			});
	}

})();
