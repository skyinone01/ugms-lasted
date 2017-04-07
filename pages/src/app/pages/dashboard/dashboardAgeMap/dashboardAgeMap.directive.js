/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardAgeMap', dashboardAgeMap);

  /** @ngInject */
  function dashboardAgeMap() {
    return {
      restrict: 'E',
      controller: 'DashboardAgeMapCtrl',
      templateUrl: 'app/pages/dashboard/dashboardAgeMap/dashboardAgeMap.html'
    };
  }
})();