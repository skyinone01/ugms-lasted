/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.theme.components')
    .controller('BaSidebarCtrl', BaSidebarCtrl);



  /** @ngInject */
  function BaSidebarCtrl($scope,$http,$q, baSidebarService) {

      $scope.menuItems = baSidebarService.getMenuItems();
      $scope.defaultSidebarState = $scope.menuItems[0].stateRef;

      $scope.hoverItem = function ($event) {
        $scope.showHoverElem = true;
        $scope.hoverElemHeight =  $event.currentTarget.clientHeight;
        var menuTopValue = 66;
        $scope.hoverElemTop = $event.currentTarget.getBoundingClientRect().top - menuTopValue;
      };

      $scope.$on('$stateChangeSuccess', function () {
        if (baSidebarService.canSidebarBeHidden()) {
          baSidebarService.setMenuCollapsed(true);
        }
      });

//    var promise = baSidebarService.notIncluded($http,$q);//获取承诺接口
//
//    promise.then(function success(result) {//成功回调
//      //$scope.menuItems = baSidebarService.getMenuItems(result);
//      next($scope,baSidebarService,result.data);
//      //fn && fn(notIn);//痛过回调,解决在控制器异步取不到数据;
//    },function error(err) {//错误回调
//      next($scope,baSidebarService,[]);
//    });


    //var notIn =notIncluded($http,baSidebarService)

    //$scope.menuItems = baSidebarService.getMenuItems(notIn);

  }

  function next($scope,baSidebarService,result){

    $scope.menuItems = baSidebarService.getMenuItems(result);
    $scope.defaultSidebarState = $scope.menuItems[0].stateRef;

    $scope.hoverItem = function ($event) {
      $scope.showHoverElem = true;
      $scope.hoverElemHeight =  $event.currentTarget.clientHeight;
      var menuTopValue = 66;
      $scope.hoverElemTop = $event.currentTarget.getBoundingClientRect().top - menuTopValue;
    };

    $scope.$on('$stateChangeSuccess', function () {
      if (baSidebarService.canSidebarBeHidden()) {
        baSidebarService.setMenuCollapsed(true);
      }
    });
  }
})();