/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource.resourceManager', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('users.resource', {
            url: '/resource',
            templateUrl: 'app/pages/userManager/resource/resourceManager.html',
            controller: 'ResourceManagerPageCtrl',
            title: '资源管理',
            sidebarMeta: {
                order: 300,
            },
        });
    }

})();
