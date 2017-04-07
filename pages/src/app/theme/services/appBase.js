/**
 * @author roy
 * created on 10.03.2017
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.theme')
        .factory('appBase', appBase);

    /** @ngInject */
    function appBase($http,$location,$uibModal,appCommon) {

        return {

            doPost: function (uri,data,callback) {
                $http({
                    method: "POST",
                    headers: {'token':appCommon.getToken()},
                    data: data,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                       if(result.errno === 0){
                         callback(result);
                       }else{
                          appCommon.openInfoModal($uibModal,"失败："+result.error);
                       }
                }).error(function(err){
                   appCommon.openInfoModal($uibModal,"失败："+err)
                });
            },
            doPut: function (uri,data,callback) {
                $http({
                    method: "PUT",
                    headers: {'token':appCommon.getToken()},
                    data: data,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    if(result.errno === 0){
                        callback(result);
                    }else{
                        appCommon.openInfoModal($uibModal,"失败："+result.error);
                    }
                }).error(function(err){
                    appCommon.openInfoModal($uibModal,"失败："+err)
                });
            },
            doDelete: function (uri,data,callback) {
                $http({
                    method: "DELETE",
                    headers: {'token':appCommon.getToken()},
                    data: data,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    if(result.errno === 0){
                        callback(result);
                    }else{
                        appCommon.openInfoModal($uibModal,"失败："+result.error);
                    }
                }).error(function(err){
                    appCommon.openInfoModal($uibModal,"失败："+err)
                });
            },
            doGet: function (uri,params,callback) {
                $http({
                    method: "GET",
                    headers: {'token':appCommon.getToken()},
                    params: params,
                    url: appCommon.autoCompleteUrl(uri)
                }).success(function(result){
                    if(result.errno === 0){
                      callback(result);
                    }else{
                       appCommon.openInfoModal($uibModal,"失败："+result.error);
                    }
                }).error(function(err){
                   appCommon.openInfoModal($uibModal,"失败："+err)
                });
            },
            doFormData: function (uri,formData,callback) {
                $http({
                    method: "POST",
                    headers: {'token':appCommon.getToken(),'Content-Type': undefined},
                    data: formData,
                    url: appCommon.autoCompleteUrl(uri),
                    transformRequest:angular.identity,
                }).success(function(result){
                    if(result.errno === 0){
                      callback(result);
                    }else{
                       appCommon.openInfoModal($uibModal,"失败："+result.error);
                    }
                }).error(function(err){
                   appCommon.openInfoModal($uibModal,"失败："+err)
                });
            },
            getToken: function(){
               return appCommon.getToken($uibModal);
            },
            autoCompleteUrl: function(uri){
               return appCommon.autoCompleteUrl(uri);
            },
            bubMsg: function(msg){
               appCommon.openInfoModal($uibModal,msg);
            },
            goLogin:function(){
                appCommon.goLogin();
            },
            goError:function(){
                appCommon.goError();
            }
        };
    }

})();