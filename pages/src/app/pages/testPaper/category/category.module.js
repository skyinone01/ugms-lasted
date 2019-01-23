/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.testPaper.category', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('question.category', {
              url: '/category',
              templateUrl: 'app/pages/testPaper/category/category.html',
              controller: "QuestionCategoryPageCtrl",
              title: '文章分类管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
