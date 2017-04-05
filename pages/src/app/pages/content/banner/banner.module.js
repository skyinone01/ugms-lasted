/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.banner', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content.banner', {
              url: '/banner',
              templateUrl: 'app/pages/content/banner/banner.html',
              controller: "BannerPageCtrl",
              title: 'Banner列表',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
