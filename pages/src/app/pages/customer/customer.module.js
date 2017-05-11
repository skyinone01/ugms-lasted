/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.customer', [
       'BlurAdmin.pages.customer.basic',
       'BlurAdmin.pages.customer.leaveMessage',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('customer', {
                url: '/customer',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '客服',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/customer','/customer/basic');
    }

})();
