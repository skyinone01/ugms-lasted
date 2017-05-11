/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardDeviceType', dashboardDeviceType);

  /** @ngInject */
  function dashboardDeviceType() {
    return {
      restrict: 'E',
      controller: 'DashboardDeviceTypeCtrl',
      templateUrl: 'app/pages/dashboard/dashboardDeviceType/dashboardDeviceType.html'
    };
  }
})();