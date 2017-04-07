/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardUv', dashboardUv);

  /** @ngInject */
  function dashboardUv() {
    return {
      restrict: 'E',
      controller: 'dashboardUvCtrl',
      templateUrl: 'app/pages/dashboard/dashboardUv/dashboardUv.html'
    };
  }
})();