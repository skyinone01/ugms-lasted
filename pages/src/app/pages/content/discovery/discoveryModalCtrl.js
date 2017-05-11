/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.discovery')
		.controller('discoveryModalCtrl', discoveryModalCtrl);

	/** @ngInject */
	function discoveryModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {

		$scope.showApplyDetail =false;
		appBase.doGet("type",null,function(response){
			$scope.types=response.data;

			if(modelId == 0){
				$scope.item = {
					id: 0,
					title: '',
					icon: '',
					orderId: '',
					typeId: '',
					isLink: '',
					linkUrl: '',
					context: '',
					summary: '',
					status:''
				};
			}else{
				appBase.doGet("discovery/"+modelId,null,function(response){
					$scope.item=response.data;
					$scope.item.isLink=response.data.link;
					$scope.typeId = response.data.typeId;
					for(var i =0;i<$scope.types.length;i++){
						if($scope.types[i].id == response.data.typeId){
							$scope.type = $scope.types[i]
						}
					}
					if( response.data.status==3 && op==2){
						$scope.showApplyDetail =true;
					}
					$scope.picmark = "mark"
				});
			}
		});


		$scope.setApplyType = function(x){
			$scope.typeId = x.type.id;
		}

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':2,'text':'通过'},{'value':3,'text':'不通过'}]
		$scope.islink=[{'value':true,'text':'是'},{'value':false,'text':'否'}]

		$scope.removePicture = function () {
			$scope.picture = $filter('appImage')('theme/no-photo.png');
			$scope.noPicture = true;
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
					$scope.item.icon = result;
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
			var status = $scope.item.status;
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
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.item.id);
		    formData.append('linkUrl', $scope.item.linkUrl);
			if ($scope.item.summary != null){
				formData.append('summary', $scope.item.summary);
			}
		    formData.append('context', $scope.item.context);
		    formData.append('isLink', $scope.item.isLink);
		    formData.append('typeId', $scope.typeId);
		    formData.append('title',$scope.item.title);
			if($scope.item.applydetail !=null){
				formData.append('applyDetail',$scope.item.applydetail);
			}
			if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}
		    formData.append('orderId',$scope.item.orderId);

		    appBase.doFormData("discovery",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.discovery", {}, { reload: true });
		    });
		}
	}

})();