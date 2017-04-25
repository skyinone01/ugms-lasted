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
                        if(permissions.edit(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "request":
                        if(permissions.request(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "cancel":
                        if(permissions.cancel(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "apply":
                        if(permissions.apply(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "release":
                        if(permissions.release(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "up":
                        if(permissions.up(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "down":
                        if(permissions.down(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "delete":
                        if(permissions.delete(stateRef)){
                            element.show();
                        }else {
                            element.hide();
                        }
                        break;
                    case "copy":
                        if(permissions.copy(stateRef)){
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