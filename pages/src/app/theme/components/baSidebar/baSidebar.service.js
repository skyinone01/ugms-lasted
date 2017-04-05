(function() {
  'use strict';

  angular.module('BlurAdmin.theme.components')
      .provider('baSidebarService', baSidebarServiceProvider);



  /** @ngInject */
  function baSidebarServiceProvider() {
    var staticMenuItems = [];

    this.addStaticItem = function() {
      staticMenuItems.push.apply(staticMenuItems, arguments);
    };

    /** @ngInject */
    this.$get = function($state, $http,layoutSizes,permissions) {
      return new _factory();

      function _factory() {
        var isMenuCollapsed = shouldMenuBeCollapsed();

//        this.notIncluded = function($http,q){
//
//          var deferred = q.defer();//声明承诺
//          var token = appBase.getToken();
//          $http({
//            method: "GET",
//            url: appBase.autoCompleteUrl("user/resources"),
//            headers: {'token':token},
//          }).success(function(result){
//            deferred.resolve(result);
//          }).error(function(err){
//            deferred.reject(err);
//          });
//          return deferred.promise;
//        };

        this.getMenuItems = function() {
          var states = defineMenuItemStates().filter(function(e){
//            return true;
          var name =permissions.hasPermission(e.stateRef);
          if(name!=null){
              e.title = name;
              return true;
          }else{
              return false;
          }
//          return permissions.hasPermission(e.stateRef);
            //for (var i=0;i<notIn.length;i++){
            //    return permissions.hasPermission(e.stateRef);
            //  if(e.stateRef == notIn[i].state ){
            //    return false;
            //  }
            //}
            //return true;
          });

          var menuItems = states.filter(function(item) {
            return item.level == 0;
          });

          menuItems.forEach(function(item) {
            var children = states.filter(function(child) {
              return child.level == 1 && child.name.indexOf(item.name) === 0;
            });
            item.subMenu = children.length ? children : null;
          });


          return menuItems.concat(staticMenuItems);
        };

        this.shouldMenuBeCollapsed = shouldMenuBeCollapsed;
        this.canSidebarBeHidden = canSidebarBeHidden;

        this.setMenuCollapsed = function(isCollapsed) {
          isMenuCollapsed = isCollapsed;
        };

        this.isMenuCollapsed = function() {
          return isMenuCollapsed;
        };

        this.toggleMenuCollapsed = function() {
          isMenuCollapsed = !isMenuCollapsed;
        };

        this.getAllStateRefsRecursive = function(item) {
          var result = [];
          _iterateSubItems(item);
          return result;

          function _iterateSubItems(currentItem) {
            currentItem.subMenu && currentItem.subMenu.forEach(function(subItem) {
              subItem.stateRef && result.push(subItem.stateRef);
              _iterateSubItems(subItem);
            });
          }
        };

        function defineMenuItemStates() {
          return $state.get()
              .filter(function(s) {
                return s.sidebarMeta;
              })
              .map(function(s) {
                var meta = s.sidebarMeta;
                return {
                  name: s.name,
                  title: s.title,
                  level: (s.name.match(/\./g) || []).length,
                  order: meta.order,
                  icon: meta.icon,
                  stateRef: s.name,
                };
              })
              .sort(function(a, b) {
                return (a.level - b.level) * 100 + a.order - b.order;
              });
        }

        function shouldMenuBeCollapsed() {
          return window.innerWidth <= layoutSizes.resWidthCollapseSidebar;
        }

        function canSidebarBeHidden() {
          return window.innerWidth <= layoutSizes.resWidthHideSidebar;
        }


      }

    };

  }
})();
