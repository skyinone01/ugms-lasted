/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.system', [
            'BlurAdmin.pages.system.monitor',
        ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('system', {
                url: '/system',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '系统管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/system','/system/monitor');
    }

})();
