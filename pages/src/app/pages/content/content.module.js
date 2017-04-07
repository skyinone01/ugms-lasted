/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content', [
       'BlurAdmin.pages.content.welcome',
       'BlurAdmin.pages.content.banner',
       'BlurAdmin.pages.content.advertisement',
       'BlurAdmin.pages.content.description',
       'BlurAdmin.pages.content.discovery',
       'BlurAdmin.pages.content.help',
       'BlurAdmin.pages.content.message',
       'BlurAdmin.pages.content.share',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content', {
                url: '/content',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '内容管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/content','/content/welcome');
    }

})();
