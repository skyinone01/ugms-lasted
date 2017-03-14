/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userManager', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('users', {
                url: '/users',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                controller: 'UserManagerPageCtrl',
                title: '用户管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 300,
                },
            }).state('users.user', {
            url: '/user',
            templateUrl: 'app/pages/userManager/user/userManager.html',
            title: '账号管理',
            sidebarMeta: {
                order: 0,
            },
        }).state('users.role', {
            url: '/role',
            templateUrl: 'app/pages/userManager/smart/roleManager.html',
            title: '角色管理',
            sidebarMeta: {
                order: 100,
            },
        });
        $urlRouterProvider.when('/users','/users/user');
    }

})();
