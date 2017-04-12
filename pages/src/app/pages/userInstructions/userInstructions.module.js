/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.userInstructions', [
       'BlurAdmin.pages.userInstructions.instruction',
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('instructions', {
                url: '/instructions',
                template : '<ui-view  autoscroll="true" autoscroll-body-top></ui-view>',
                abstract: true,
                title: '使用说明',
                sidebarMeta: {
                    icon: 'ion-grid',
                    order: 40,
                }
            });
        $urlRouterProvider.when('/instructions','/instructions/instruction');
    }

})();
