/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.welcome', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content.welcome', {
              url: '/welcome',
              templateUrl: 'app/pages/content/welcome/welcome.html',
              controller: "WelcomePageCtrl",
              title: '欢迎页列表',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 20,
              }
            });

    }
})();
