/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.article')
			.controller('articleModalCtrl', articleModalCtrl);

	/** @ngInject */
	function articleModalCtrl($scope,$stateParams, fileReader, $uibModal,$filter,appBase,$state) {
//		$scope.config={
//			"content" : "<p>test1</p>",
//			"focus" : true,
//			"indentValue":"2em",
//			"initialFrameWidth":1000,
//			"initialFrameHeight":320,
//			"readonly" : false ,
//			"enableAutoSave": false,
//			"saveInterval": 500,
//			"fullscreen" : false,
//			"imagePopup":true,
//			"allHtmlEnabled":false,
//			"functions" :['map','insertimage','insertvideo','attachment','insertcode','template', 'background', 'wordimage']
//		};
		$scope.getContent=function(id){
			var content=$scope.ueditorGetContent(id);
			alert(content);
		}
		$scope.getContentTxt=function(id){
			var content=$scope.ueditorGetContentTxt(id);
		}
		$scope.setContent=function(){
			$scope.ueditorSetContent("container","111111");
		}


        $scope.listItem = function(){

            if(modelId == 0){
                $scope.item = {
                    id: 0,
                    title: '',
                    typestr: '',
                    typeid:0,
                    summary:'',
                    content:'',
                    source:'',
                    author:'',
                    status:0,
                };
            }else{
                appBase.doGet("article/"+modelId,null,function(response){
                    $scope.item=response.data;
                    //$("#data_date").val(response.data.begin_date)
                    for(var i =0;i<$scope.categories.length;i++){
                        if($scope.categories[i].value == response.data.type){
                            $scope.type = $scope.categories[i];
                        }
                    }
                    if( response.data.status==3 && op==2){
                        $scope.showApplyDetail =true;
                    }
                    $scope.typeid = response.data.typeid;
                    $scope.typestr = response.data.typestr;
                    $scope.picmark = "mark"
			    });
            }

        }

		var modelId= $stateParams.modelId,op=$stateParams.op;
		$scope.showApplyDetail =false;
		$scope.labels=[{'id':0,'name':'养生'},{'id':1,'name':'健康'}]
		$scope.listCategory = function(){
            appBase.doGet("articleCategoryList",null,function(response){
                 $scope.categories = response.data.slice(0);
                 $scope.listItem();
            })
		}
        $scope.listCategory();

        $scope.removeLabel = function(labelId){
            var param = "addable=0&article="+modelId+"&label="+labelId;
            appBase.doPut("article/label?"+param,null,function(response){
				appBase.bubMsg("操作成功");
				$scope.listItem();
			})
        }

		$scope.addLabel = function(){
            if(modelId == 0){
		        appBase.bubMsg("请先保存文章再添加标签");
		        return;
		    }

			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/pages/article/article/articleLabelModal.html',
				size: 'lg',
				backdrop:"static",
				controller: 'articleLabelModalCtrl',
				resolve: {
					columnId:  function() { return modelId; }
				}
			});

			modalInstance.result.then(function (result) {
                 $scope.listItem();
            }, function (reason) {
                $scope.listItem();
                console.log(reason);//点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
             });
		}

		//op 1 新增 2详情 3编辑 4 审核
		$scope.initInput= function(){
			if(op==2 || op==4){
				return true;
			}else {
				return false;
			}
		}
		$scope.showSubmit = function(){
			if(op ==2){
				return false;
			}else {
				return true
			}
		}

		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};
		$scope.showApply = function(){
			if(op ==4){
				return true;
			}else {
				return false
			}
		}

		$scope.setApplyStatus = function(){
			var status = $scope.welcome.status;
			if (status ==2 || status ==3){
				$scope.applyStatus = status;
				if(status==3){
					$scope.showApplyDetail =true;
				}else {
					$scope.showApplyDetail =false;
				}
			}
		}
		$scope.selectType = function(x){
			$scope.typeid = x.category.value;
			$scope.typestr = x.category.text;
		}
		$scope.saveOrUpdate = function(dismis){

			var formData = new FormData();
			formData.append('file',$scope.file);
			formData.append('id', $scope.item.id);
			formData.append('title',$scope.item.title);
			formData.append('content',$scope.item.content);
			formData.append('summary',$scope.item.summary);
			formData.append('source',$scope.item.source);
			formData.append('author',$scope.item.author);
			formData.append('typeid',$scope.typeid);
			formData.append('typestr',$scope.typestr);
			if($scope.welcome.applydetail !=null){
				formData.append('applyDetail',$scope.welcome.applydetail);
			}
			if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}

			appBase.doFormData("article",formData,function(response){
				appBase.bubMsg("保存成功");
				dismis;
				$state.go("article.article", {}, { reload: true });
			});
		}
	}

})();