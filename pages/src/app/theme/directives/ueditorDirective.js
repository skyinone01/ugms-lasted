(function() {
    'use strict';
    var page = angular.module('ueditor.directive', []);
    page.directive('ueditor', [
        '$templateCache',
        function($templateCache) {
            return {
                restrict : 'AE',
                template : '<script id="ueditorId" name="content" type="text/plain">文章编辑</script>',
                scope : false,
                compile: function(element, attr) {
                    return {
                        pre: function(scope, iElement, iAttrs, controller) {
                            var editorFunctions=[
                                'fullscreen', 'source', '|', 'undo', 'redo', '|',
                                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript',
                                'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
                                'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                                'fontfamily', 'fontsize', 'indent', '|',
                                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                                'link', 'unlink',  '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                                'simpleupload', 'insertimage',  '|',
                                'emotion','horizontal', 'spechars',  'inserttable'
                            ] ;
                            scope.ueditorId=attr.id;
                            scope.config={};
                            if(attr.config!=''&&attr.config!=undefined){
                                //var configf ={content : '<p>test1</p>',focus : true,indentValue:'2em',initialFrameWidth:1000,initialFrameHeight:320,readonly : false ,enableAutoSave: false,saveInterval: 500,fullscreen : false,imagePopup:true,allHtmlEnabled:false,functions :['map','insertimage','insertvideo','attachment','insertcode','template', 'background', 'wordimage']};
                                //scope.config=$.parseJSON(configf);
                                scope.config={
                                    "content" : "<p>test1</p>",
                                    "focus" : true,
                                    "indentValue":"2em",
                                    "initialFrameWidth":1000,
                                    "initialFrameHeight":320,
                                    "readonly" : false ,
                                    "enableAutoSave": false,
                                    "saveInterval": 500,
                                    "fullscreen" : false,
                                    "imagePopup":true,
                                    "allHtmlEnabled":false,
                                    "functions" :['map','insertimage','insertvideo','attachment','insertcode','template', 'background', 'wordimage']
                                }
                                editorFunctions=editorFunctions.concat(scope.config.functions);
                            }

                            UE.delEditor(scope.ueditorId);
                            var editor = UE.getEditor(scope.ueditorId,{
                                toolbars: [editorFunctions] ,
                                initialContent : scope.config.content?scope.config.content:'',
                                focus: scope.config.focus?scope.config.focus:false,
                                indentValue:scope.config.indentValue?scope.config.indentValue:'2em',
                                initialFrameWidth:scope.config.initialFrameWidth?scope.config.initialFrameWidth:1000,  //初始化编辑器宽度,默认1000
                                initialFrameHeight:scope.config.initialFrameHeight?scope.config.initialFrameHeight:320, //初始化编辑器高度,默认320
                                readonly : scope.config.readonly?scope.config.readonly:false ,//编辑器初始化结束后,编辑区域是否是只读的，默认是false
                                enableAutoSave: scope.config.enableAutoSave?scope.config.enableAutoSave:true,     //启用自动保存
                                saveInterval: scope.config.saveInterval?scope.config.saveInterval:500,  //自动保存间隔时间， 单位ms
                                fullscreen : scope.config.fullscreen?scope.config.fullscreen:false,//是否开启初始化时即全屏，默认关闭
                                imagePopup: scope.config.imagePopup?scope.config.imagePopup:true,     //图片操作的浮层开关，默认打开
                                allHtmlEnabled:scope.config.allHtmlEnabled?scope.config.allHtmlEnabled:false //提交到后台的数据是否包含整个html字符串
                            });

                            editor.ready(function(){

                            });

                            scope.ueditorSetContent=function(id,value){
                                var editor = UE.getEditor(id);
                                editor.setContent(value);
                            }

                            scope.ueditorGetContent=function(id){
                                var editor = UE.getEditor(id);
                                return editor.getContent();
                            }

                            scope.ueditorGetContentTxt=function(id){
                                var editor = UE.getEditor(id);
                                return editor.getContentTxt();
                            }
                        },
                        post: function(scope, iElement, iAttrs, controller) {

                        }
                    }
                }
            }
        } ]);

})();