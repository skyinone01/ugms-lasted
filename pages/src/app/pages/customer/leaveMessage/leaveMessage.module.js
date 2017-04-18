/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.customer.leaveMessage', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('customer.leaveMessage', {
              url: '/leaveMessage',
              templateUrl: 'app/pages/customer/leaveMessage/leaveMessage.html',
              controller: "LeaveMessagePageCtrl",
              title: '留言管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 30,
              }
            });

    }
})();
