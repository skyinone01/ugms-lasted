/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.discovery', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content.discovery', {
              url: '/discovery',
              templateUrl: 'app/pages/content/discovery/discovery.html',
              controller: "DiscoveryPageCtrl",
              title: '发现列表',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
