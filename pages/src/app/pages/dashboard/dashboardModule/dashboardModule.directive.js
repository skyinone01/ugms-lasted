/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardModule', dashboardModule);

  /** @ngInject */
  function dashboardModule() {
    return {
      restrict: 'E',
      controller: 'DashboardModuleCtrl',
      templateUrl: 'app/pages/dashboard/dashboardModule/dashboardModule.html'
    };
  }
})();