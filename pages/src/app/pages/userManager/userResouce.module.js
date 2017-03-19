/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userResource', [

       'BlurAdmin.pages.userResource.userManager',
       'BlurAdmin.pages.userResource.roleManager',
       'BlurAdmin.pages.userResource.resourceManager',

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('users', {
                url: '/users',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '用户资源管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/users','/users/user');
    }

})();
