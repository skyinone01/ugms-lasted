/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.advertisement', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {


        $stateProvider
            .state('content.advertisement', {
              url: '/advertisement',
              templateUrl: 'app/pages/content/advertisement/advertisement.html',
              controller: "AdvertisementPageCtrl",
              title: '广告列表',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
