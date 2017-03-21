/**
 * @author a.demeshko
 * created on 18.01.2016
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages.common')
    .controller('infoModalCtrl', infoModalCtrl);

  /** @ngInject */
  function infoModalCtrl($scope, $uibModal, content) {
    $scope.content = content;
    $scope.title = "提示";

  }

})();
