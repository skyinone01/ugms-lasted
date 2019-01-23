/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.testPaper.testPaper')
			.controller('testPaperModalCtrl', testPaperModalCtrl);

	/** @ngInject */
	function testPaperModalCtrl($scope,$stateParams, fileReader, $uibModal,$filter,appBase,$state) {

		var modelId= $stateParams.modelId,op=$stateParams.op,title = $stateParams.title;
		$scope.showApplyDetail =false;

		$scope.months=[{'value':1,'text':'1个月'},{'value':2,'text':'2个月'},{'value':3,'text':'3个月'},{'value':4,'text':'4个月'},
			{'value':5,'text':'5个月'},{'value':6,'text':'6个月'},{'value':7,'text':'7个月'},{'value':8,'text':'8个月'},
			{'value':9,'text':'9个月'},{'value':10,'text':'10个月'},{'value':11,'text':'11个月'},{'value':12,'text':'12个月'}]

        $scope.listItems = function(){
            appBase.doGet("articleColumn/"+modelId,null,function(response){
				$scope.item=response.data;
				if( response.data.status==3 && op==2){
					$scope.showApplyDetail =true;
				}
				if(response.data.paymode){
					for(var i =0;i<$scope.months.length;i++){
						if($scope.months[i].value == response.data.payItem1.month){
							$scope.item.payItem1.month = $scope.months[i];
						}
						if($scope.months[i].value == response.data.payItem2.month){
							$scope.item.payItem2.month = $scope.months[i];
						}
						if($scope.months[i].value == response.data.payItem3.month){
							$scope.item.payItem3.month = $scope.months[i];
						}
					}

				}
				$scope.picmark = "mark"
			});
        }
		if(modelId == 0){
			$scope.item = {
				id: 0,
				title: '',
				url: '',
				paymode: true,
				payitem1:{price:0,month:0},
				payitem2:{price:0,month:0},
				payitem3:{price:0,month:0},
				link: '',
				articles:[],
				aplydetail:'',
				applypeole:''
			};
		}else{
             $scope.listItems();
		}

        $scope.deleteArticle = function(id){
            var param = "addable=0&column="+modelId+"&article="+id;
            appBase.doPut("articleColumn?"+param,null,function(response){
                $scope.listItems();
				appBase.bubMsg("操作成功");
			})
        }

		$scope.showSubmit = function(){
			if(op ==2){
				return false;
			}else {
				return true
			}
		}


		$scope.removeLabel = function (id) {
			//$scope.picture = $filter('appImage')('theme/no-photo.png');
			//$scope.noPicture = true;
		};

		//op 1 新增 2详情 3编辑 4 审核
		$scope.initInput= function(){
			if(op==2 || op==4){
				return true;
			}else {
				return false;
			}
		}

		$scope.uploadPicture = function () {
			var fileInput = document.getElementById('uploadFile');
			fileInput.click();
		};
		$scope.showSubmit = function(){
			if(op ==2){
				return false;
			}else {
				return true
			}
		}
		$scope.getFile = function (file) {
			fileReader.readAsDataUrl(file, $scope)
					.then(function (result) {
						$scope.item.picture = result;
						$scope.picmark="mark";
					});
			$scope.file = file;
		};


        //o添加文章
		$scope.open = function (page, size,name) {
		    if(modelId == 0){
		        appBase.bubMsg("请先保存专栏再添加文章");
		        return;
		    }
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				backdrop:"static",
				controller: 'columnArticleModalCtrl',
				resolve: {
					articleName:  function() { return angular.copy(name); },
					columnId:  function() { return modelId; },
					title:  function() { return angular.copy(title);  },
					op:  function() { return op;  },
				}
			});

			modalInstance.result.then(function (result) {
                 $scope.listItems();
            }, function (reason) {
                $scope.listItems();
                console.log(reason);//点击空白区域，总会输出backdrop click，点击取消，则会暑促cancel
             });
		};
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
		$scope.saveOrUpdate = function(dismis){
            var formData = new FormData();
            formData.append('id',$scope.item.id);
		    if(op == 10){
	            if($scope.item.applydetail !=null){
    				formData.append('applyDetail',$scope.item.applydetail);
    			}
                if ($scope.applyStatus != null){
    				formData.append('status',$scope.applyStatus);
    			}


		    }else{

			    formData.append('file',$scope.file);
			    formData.append('title',$scope.item.title);
                formData.append('type',$scope.typeId);
                formData.append('paymode',$scope.item.paymode);
                if($scope.item.paymode){
                    formData.append('payitem1',$scope.item.payItem1.price+"_"+$scope.item.payItem1.month.value);
                    formData.append('payitem2',$scope.item.payItem2.price+"_"+$scope.item.payItem2.month.value);
                    formData.append('payitem3',$scope.item.payItem3.price+"_"+$scope.item.payItem3.month.value);
                }

		    }
			appBase.doFormData("articleColumn",formData,function(response){
				appBase.bubMsg("保存成功");
				dismis;
				$state.go("article.column", {}, { reload: true });
			});
		}
	}

})();