/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.article.column', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('article.column', {
              url: '/column',
              templateUrl: 'app/pages/article/column/column.html',
              controller: "ColumnPageCtrl",
              title: '专栏管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 20,
              }
            }).state('article.column.add', {
            url: '/:modelId/:op',
            templateUrl: 'app/pages/article/column/columnModal.html',
            title: '专栏操作',
            controller: "columnModalCtrl"
        });

    }
})();
