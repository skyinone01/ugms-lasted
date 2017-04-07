/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.dashboard')
      .directive('dashboardUser', dashboardUser);

  /** @ngInject */
  function dashboardUser() {
    return {
      restrict: 'E',
      controller: 'dashboardUserCtrl',
      templateUrl: 'app/pages/dashboard/dashboardUser/dashboardUser.html'
    };
  }
})();