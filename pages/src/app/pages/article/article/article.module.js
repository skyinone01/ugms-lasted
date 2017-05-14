/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.article.article', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('article.article', {
              url: '/article',
              templateUrl: 'app/pages/article/article/article.html',
              controller: "ArticlePageCtrl",
              title: '文章管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 10,
              }
            }).state('article.article.add', {
            url: '/:modelId/:op',
            templateUrl: 'app/pages/article/article/articleModal.html',
            title: '文章操作',
            controller: "articleModalCtrl"
        });

    }
})();
