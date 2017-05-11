/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardMapType', dashboardMapType);

  /** @ngInject */
  function dashboardMapType() {
    return {
      restrict: 'E',
      controller: 'DashboardMapTypeCtrl',
      templateUrl: 'app/pages/dashboard/dashboardMapType/dashboardMapType.html'
    };
  }
})();