/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardSex', dashboardSex);

  /** @ngInject */
  function dashboardSex() {
    return {
      restrict: 'E',
      controller: 'DashboardSexCtrl',
      templateUrl: 'app/pages/dashboard/dashboardSex/dashboardSex.html'
    };
  }
})();