/**
 * @author roy
 * created on 10.03.2017
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .factory('appBase', appBase);

    /** @ngInject */
    function appBase($http,$location) {
        var methods = {};
        var progress = 0;
        var max = 100;
        var opened = false;

        this.goLogin = function () {
            alert("会话超时");
            $location.href = "http://localhost:3000/auth.html";
        };
        this.goError = function () {
            $location.href = "http://localhost:3000/404.html";
        };

        return {
            doPost: function (url,data,callback) {
                $http({
                    method: "POST",
                    headers: {'token':getToken},
                    data: data,
                    url: url
                }).success(function(result){
                    callback(result);
                }).error(function(err){
                   alert("请求失败:"+err);
                });
            },
            doGet: function (url,params,callback) {
                $http({
                    method: "GET",
                    headers: {'token':getToken},
                    params: params,
                    url: url
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
                    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
                }else {
                    goLogin();
                }
                return token;
            }
        };
    }

})();