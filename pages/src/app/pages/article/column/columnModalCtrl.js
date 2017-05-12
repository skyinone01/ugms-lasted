/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.column')
			.controller('columnModalCtrl', columnModalCtrl);

	/** @ngInject */
	function columnModalCtrl($scope,$stateParams, fileReader, $filter,appBase,$state) {

		var modelId= $stateParams.modelId,op=$stateParams.op;
		$scope.showApplyDetail =false;

		$scope.months=[{'value':1,'text':'1个月'},{'value':2,'text':'2个月'},{'value':3,'text':'3个月'},{'value':4,'text':'4个月'},
			{'value':5,'text':'5个月'},{'value':6,'text':'6个月'},{'value':7,'text':'7个月'},{'value':8,'text':'8个月'},
			{'value':9,'text':'9个月'},{'value':10,'text':'10个月'},{'value':11,'text':'11个月'},{'value':12,'text':'12个月'}]

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
                    formData.append('payitem1',$scope.item.payitem1.price+"_"+$scope.item.payitem1.month.value);
                    formData.append('payitem2',$scope.item.payitem2.price+"_"+$scope.item.payitem2.month.value);
                    formData.append('payitem3',$scope.item.payitem3.price+"_"+$scope.item.payitem3.month.value);
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