/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userStatistics', [
       'BlurAdmin.pages.userStatistics.statistic',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('statistics', {
                url: '/statistics',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '用户统计',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                }
            });
        $urlRouterProvider.when('/statistics','/statistics/statistic');
    }

})();
