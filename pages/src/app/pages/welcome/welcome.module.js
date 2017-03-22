/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.welcome', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('welcome', {
              url: '/welcome',
              templateUrl: 'app/pages/welcome/welcomes.html',
              controller: "WelcomePageCtrl",
              title: '欢迎页列表',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 20,
              }
            });

    }
})();
