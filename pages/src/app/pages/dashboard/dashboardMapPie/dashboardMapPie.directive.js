/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardMapPie', dashboardMapPie);

  /** @ngInject */
  function dashboardMapPie() {
    return {
      restrict: 'E',
      controller: 'DashboardMapPieCtrl',
      templateUrl: 'app/pages/dashboard/dashboardMapPie/dashboardMapPie.html'
    };
  }
})();