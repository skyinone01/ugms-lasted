(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .directive('hasPermission', hasPermission);


    function hasPermission(permissions) {
        return {
            link: function(scope, element, attrs) {
                //if(!_.isString(attrs.hasPermission))
                //    throw "hasPermission value must be a string";

                var value = attrs.hasPermission.split(":");
                var stateRef = value[0];
                var flag = value[1];
                
                //
                //function toggleVisibilityBasedOnPermission() {
                //    var hasPermission = permissions.hasPermission(value);
                //
                //    if(hasPermission && !notPermissionFlag || !hasPermission && notPermissionFlag)
                //        element.show();
                //    else
                //        element.hide();
                //}
                //toggleVisibilityBasedOnPermission();
                //scope.$on('permissionsChanged', toggleVisibilityBasedOnPermission);
            }
        };
    }

})();