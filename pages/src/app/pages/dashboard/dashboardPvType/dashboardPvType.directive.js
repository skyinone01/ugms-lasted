/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardPvType', dashboardPvType);

  /** @ngInject */
  function dashboardPvType() {
    return {
      restrict: 'E',
      controller: 'DashboardPvTypeCtrl',
      templateUrl: 'app/pages/dashboard/dashboardPvType/dashboardPvType.html'
    };
  }
})();