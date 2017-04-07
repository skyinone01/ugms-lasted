/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardSexPie', dashboardSexPie);

  /** @ngInject */
  function dashboardSexPie() {
    return {
      restrict: 'E',
      controller: 'dashboardSexPieCtrl',
      templateUrl: 'app/pages/dashboard/dashboardSexPie/dashboardSexPie.html'
    };
  }
})();