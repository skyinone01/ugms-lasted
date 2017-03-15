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
                    headers: {'token':appCommon.getToken()},
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
                    headers: {'token':appCommon.getToken()},
                    params: params,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    callback(result);
                }).error(function(err){
                    alert("请求失败:"+err);
                });
            },
            getToken: function(){
               return appCommon.getToken();
            },
            autoCompleteUrl: function(uri){
               return appCommon.autoCompleteUrl(uri);
            }
        };
    }

})();