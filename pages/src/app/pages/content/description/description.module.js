/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.description', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content.description', {
              url: '/description',
              templateUrl: 'app/pages/content/description/description.html',
              controller: "DescriptionPageCtrl",
              title: '说明/注意事项',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
