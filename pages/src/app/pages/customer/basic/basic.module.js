/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.customer.basic', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('customer.basic', {
              url: '/basic',
              templateUrl: 'app/pages/customer/basic/basic.html',
              controller: "BasicPageCtrl",
              title: '基础管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
