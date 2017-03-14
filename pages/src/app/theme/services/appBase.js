/**
 * @author roy
 * created on 10.03.2017
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .factory('appBase', appBase);

    /** @ngInject */
    function appBase($http,$location,appCommon) {
        var methods = {};
        var progress = 0;
        var max = 100;
        var opened = false;

        return {
            doPost: function (uri,data,callback) {
                $http({
                    method: "POST",
                    headers: {'token':getToken()},
                    data: data,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    callback(result);
                }).error(function(err){
                   alert("请求失败:"+err);
                });
            },
            doGet: function (uri,params,callback) {
                $http({
                    method: "GET",
                    headers: {'token':getToken()},
                    params: params,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    callback(result);
                }).error(function(err){
                    alert("请求失败:"+err);
                });
            },

            getToken:function(){
                var arr,token,reg=new RegExp("(^| )"+"token"+"=([^;]*)(;|$)");
                if(arr=document.cookie.match(reg)){
                      token = unescape(arr[2]);
                      var Days = 1/12;
                      var exp = new Date();
                      exp.setTime(exp.getTime() + Days*24*60*60*1000);
                      document.cookie = "token" + "="+ escape (token) + ";expires=" + exp.toGMTString();
                }else {
                      alert("会话超时");
                      appCommon.goLogin();
                }
                return token;
            }
        };
    }

})();