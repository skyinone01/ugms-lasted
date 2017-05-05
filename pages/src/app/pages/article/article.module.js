/**
 * @author Roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.article', [
       'BlurAdmin.pages.article.article',
       'BlurAdmin.pages.article.category',
       'BlurAdmin.pages.article.column',
       'BlurAdmin.pages.article.label',
        'ng.ueditor',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('article', {
                url: '/article',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '软文管理',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                },
            });
        $urlRouterProvider.when('/article','/article/article');
    }

})();
