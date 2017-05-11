/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.article.category', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('article.category', {
              url: '/category',
              templateUrl: 'app/pages/article/category/category.html',
              controller: "CategoryPageCtrl",
              title: '文章分类管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
