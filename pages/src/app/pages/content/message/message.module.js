/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.message', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('content.message', {
              url: '/message',
              templateUrl: 'app/pages/content/message/message.html',
              controller: "MessagePageCtrl",
              title: '消息提醒',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
