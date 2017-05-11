/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.article.label', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('article.label', {
              url: '/label',
              templateUrl: 'app/pages/article/label/label.html',
              controller: "LabelPageCtrl",
              title: '标签管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
              }
            });

    }
})();
