/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.testPaper', [

       'BlurAdmin.pages.testPaper.question',
       'BlurAdmin.pages.testPaper.category',
        'BlurAdmin.pages.testPaper.testPaper',

    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('testPaper', {
                url: '/testPaper',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '用户资源管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/testPaper','/testPaper/testPaper');
    }

})();
