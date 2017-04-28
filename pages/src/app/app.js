'use strict';
//var API_ROOT ="http://120.26.221.137:8080/";
//var PAGES_ROOT ="http://120.26.221.137:3000/ugms/";

var API_ROOT ="http://127.0.0.1:8080/";
var PAGES_ROOT ="http://127.0.0.1:3000/";

function getToken(){
    var arr,token,reg=new RegExp("(^| )"+"token"+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        token = unescape(arr[2]);
        var Days = 1/48;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days*24*60*60*1000);
        document.cookie = "token" + "="+ escape (token) + ";expires=" + exp.toGMTString()+ "; path=/";
    }else {

         window.location.href = PAGES_ROOT +"auth.html";
    }
    return token;
}
function setCookie(name,value){
      var Days = 1/48;
      var exp = new Date();
      exp.setTime(exp.getTime() + Days*24*60*60*1000);
      document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+ ";path=/";
}

function goError(){
    window.location.href = PAGES_ROOT+"404.html";
}
function goLogin(){
    window.location.href = PAGE_ROOT+"auth.html";
}

// start ---
var app = angular.module('BlurAdmin', [
            'ngAnimate',
            'ui.bootstrap',
            'ui.sortable',
            'ui.router',
            'ngTouch',
            'toastr',
            'smart-table',
            "xeditable",
            'ui.slimscroll',
            'ngJsTree',
            //'ng.ueditor',
            'angular-progress-button-styles',

            'BlurAdmin.theme',
            'BlurAdmin.pages'
          ]), permissionList;

app.run(function(permissions) {
  permissions.setPermissions(permissionList)
});

angular.element(document).ready(function() {
   var token = window.location.href;
   if(token.indexOf("token") != -1){
      setCookie("token",token.split("token=")[1]);
   }
   $.ajax({
     url: API_ROOT + "user/resources",
     type: "GET",
     headers:{'token':getToken()},
     success: function(data) {
        permissionList = data.data;
        angular.bootstrap(document, ['BlurAdmin']);
     },
     error:function(error){
        window.location.href = PAGES_ROOT +"auth.html";
     }
   });
});


// end---
angular.module('BlurAdmin')
  .factory('permissions', function ($rootScope) {
    var permissionList;
    return {
      setPermissions: function(permissions) {
           permissionList = permissions;
           $rootScope.$broadcast('permissionsChanged')
      },
      hasPermission: function (stateRef) {
              stateRef = stateRef.trim();
              for(var i=0;i<permissionList.length;i++){
                 var item = permissionList[i];
                 if( item.stateRef.trim() === stateRef){
                    return item.name;
                 }
              }
              return null;
//              return permissionList.some(function(item) {
//                return item.stateRef.trim() === stateRef
//              });
      },
        edit: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.editable == true){
                    return true;
                }
            }
            return false;

        },
        delete: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.deleteable == true){
                    return true;
                }
            }
            return false;

        },
        request: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.requestable == true){
                    return true;
                }
            }
            return false;
        },
        cancel: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.cancelable == true){
                    return true;
                }
            }
            return false;
        },
        apply: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.applyable == true){
                    return true;
                }
            }
            return false;
        },
        release: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.releaseable == true){
                    return true;
                }
            }
            return false;
        },
        up: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.upable == true){
                    return true;
                }
            }
            return false;
        },
        down: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.downable == true){
                    return true;
                }
            }
            return false;
        },
        copy: function (stateRef) {
            stateRef = stateRef.trim();
            for(var i=0;i<permissionList.length;i++){
                var item = permissionList[i];
                if( item.stateRef.trim() === stateRef && item.copyable == true){
                    return true;
                }
            }
            return false;
        }
   };
  });

