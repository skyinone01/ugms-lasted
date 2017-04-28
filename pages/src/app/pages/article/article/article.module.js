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
            });

    }
})();
