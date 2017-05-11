/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardDevice', dashboardDevice);

  /** @ngInject */
  function dashboardDevice() {
    return {
      restrict: 'E',
      controller: 'DashboardDeviceCtrl',
      templateUrl: 'app/pages/dashboard/dashboardDevice/dashboardDevice.html'
    };
  }
})();