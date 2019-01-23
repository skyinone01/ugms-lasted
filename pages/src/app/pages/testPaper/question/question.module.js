/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.testPaper.question', [])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider.state('testPaper.question', {
            url: '/question',
            templateUrl: 'app/pages/testPaper/question/question.html',
            controller: 'TestPaperPageCtrl',
            title: '题目管理',
            sidebarMeta: {
                order: 200,
            },
        });
    }

})();
