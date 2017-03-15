/**
 * @author v.lugovsky
 * created on 15.12.2015
 */
(function () {
  'use strict';

  var IMAGES_ROOT = 'assets/img/';
  var URL_ROOT = 'http://localhost:8080/';

  angular.module('BlurAdmin.theme')
    .constant('layoutSizes', {
      resWidthCollapseSidebar: 1200,
      resWidthHideSidebar: 500
    })
    .constant('layoutPaths', {
      images: {
        root: IMAGES_ROOT,
        profile: IMAGES_ROOT + 'app/profile/',
        amMap: 'assets/img/theme/vendor/ammap//dist/ammap/images/',
        amChart: 'assets/img/theme/vendor/amcharts/dist/amcharts/images/'
      }
    })
    .constant('appCommon',{
        autoCompleteUrl:function(uri){
            return URL_ROOT + uri;
        },
        goLogin:function(){
            window.location.href = URL_ROOT+"auth.html";
        },
        goError:function () {
            window.location.href = URL_ROOT+"404.html";
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
               window.location.href = URL_ROOT+"auth.html";
            }
            return token;
        },
    })
    .constant('colorHelper', {
      tint: function(color, weight) {
        return mix('#ffffff', color, weight);
      },
      shade: function(color, weight) {
        return mix('#000000', color, weight);
      },
    });

  function shade(color, weight) {
    return mix('#000000', color, weight);
  }

  function tint(color, weight) {
    return mix('#ffffff', color, weight);
  }

  //SASS mix function
  function mix(color1, color2, weight) {
    // convert a decimal value to hex
    function d2h(d) {
      return d.toString(16);
    }
    // convert a hex value to decimal
    function h2d(h) {
      return parseInt(h, 16);
    }

    var result = "#";
    for(var i = 1; i < 7; i += 2) {
      var color1Part = h2d(color1.substr(i, 2));
      var color2Part = h2d(color2.substr(i, 2));
      var resultPart = d2h(Math.floor(color2Part + (color1Part - color2Part) * (weight / 100.0)));
      result += ('0' + resultPart).slice(-2);
    }
    return result;
  }
})();
