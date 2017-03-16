'use strict';


function getToken(){
    var arr,token,reg=new RegExp("(^| )"+"token"+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
        token = unescape(arr[2]);
        var Days = 1/48;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days*24*60*60*1000);
        document.cookie = "token" + "="+ escape (token) + ";expires=" + exp.toGMTString();
    }else {
         alert("会话超时，返回登陆页面")
         window.location.href = "http://127.0.0.1:3000/auth.html";
    }
    return token;
}
var permission;
angular.element(document).ready(function() {
   alert("hello");
   var token = getToken();
   $.ajax({
     url: "http://127.0.0.1:8080/user/resources",
     type: "GET",
     beforeSend: function(xhr){xhr.setRequestHeader('token', token);},
     success: function(data) {
        permission = data.data;
     },
     error:function(error){
        alert("加载权限失败 " + error.error);
        window.location.href = "http://127.0.0.1:3000/auth.html";
     }
   });
});

angular.module('BlurAdmin', [
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
  'angular-progress-button-styles',

  'BlurAdmin.theme',
  'BlurAdmin.pages'
]);
