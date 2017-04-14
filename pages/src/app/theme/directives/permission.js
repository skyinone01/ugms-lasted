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
                switch (flag){
                    case "edit":
                        if(permissions.editable(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "delete":
                        if(permissions.deleteable(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "operate":
                        if(permissions.operateable(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;

                }
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