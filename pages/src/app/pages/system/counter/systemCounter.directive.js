/**
 * @author Roy
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.system.monitor')
      .directive('systemCounter', systemCounter);

  /** @ngInject */
  function systemCounter() {
    return {
      restrict: 'E',
      controller: 'SystemCounterPageCtrl',
      templateUrl: 'app/pages/system/counter/systemCounterLineChart.html',
      scope:{
        chartId:'@'
      },
    };
  }
})();