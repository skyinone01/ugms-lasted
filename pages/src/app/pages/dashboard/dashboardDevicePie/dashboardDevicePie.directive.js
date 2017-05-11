/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardDevicePie', dashboardDevicePie);

  /** @ngInject */
  function dashboardDevicePie() {
    return {
      restrict: 'E',
      controller: 'dashboardDevicePieCtrl',
      templateUrl: 'app/pages/dashboard/dashboardDevicePie/dashboardDevicePie.html'
    };
  }
})();