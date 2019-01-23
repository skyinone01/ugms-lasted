/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.testPaper.testPaper', [
    ])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('testPaper.testPaper', {
              url: '/testPaper/testPaper',
              templateUrl: 'app/pages/testPaper/testPaper/testPaper.html',
              controller: "TestPaperPageCtrl",
              title: '试卷管理',
              sidebarMeta: {
                    icon: 'ion-grid',
                    order: 20,
              }
            }).state('testPaper.testPaper.add', {
            url: '/:modelId/:op',
            templateUrl: 'app/pages/testPaper/testPaper/testPaperModal.html',
            title: '试卷操作',
            controller: "testPaperModalCtrl"
        });

    }
})();
