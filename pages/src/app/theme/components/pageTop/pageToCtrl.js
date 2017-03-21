/**
 * @author v.Roy
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.theme.components')
    .controller('PageTopCtrl', PageTopCtrl);



  /** @ngInject */
  function PageTopCtrl($scope,appBase) {

      $scope.logOut = function () {
        appBase.doPost("token",null,function(){
          appBase.bubMsg("成功登出，谢谢使用");
          appBase.goLogin();
        })
      };


  }
})();