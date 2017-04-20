/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system.monitor', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('system.monitor', {
            url: '/monitor',
            templateUrl: 'app/pages/system/counter/systemCounter.html',
            controller: 'SystemCounterPageCtrl',
            title: '资源监控',
            sidebarMeta: {
                order: 10,
            },
        });
    }

})();
