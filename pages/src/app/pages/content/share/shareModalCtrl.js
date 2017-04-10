/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.share')
		.controller('shareModalCtrl', shareModalCtrl);

	/** @ngInject */
	function shareModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {
		$scope.listTypes = function(){
			appBase.doGet("types",null,function(response){
				$scope.typeOptions=response.data;
			});
		}
		$scope.types = [{'value':2,'text':'通过'},{'value':3,'text':'不通过'}];
		$scope.listTypes();

		 if(modelId == 0){
		    $scope.item = {
         			id: 0,
         			title: '',
         			url: '',
				    pictures: '',
				    linkUrl: '',
         			context: '',
         			summary: '',
         			orderId: '',
         			typeId: '',
         			typeStr: '',
         			status:''
         	};
		 }else{
		     appBase.doGet("message/"+modelId,null,function(response){
                 $scope.item=response.data;
				 $scope.typeStr = $scope.item.typeStr;
				 $scope.picmark = "mark"
		     });
		 }

		//$scope.types = [{'value':2,'text':'通过'},{'value':3,'text':'不通过'}];

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':2,'text':'通过'},{'value':3,'text':'不通过'}];

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
			var status = $scope.item.status;
			if (status ==2 || status ==3){
				$scope.applyStatus = status;
			}
		}

		$scope.setApplyType = function(){
			$scope.typeStr = $scope.item.typeStr;
		}
		$scope.saveOrUpdate = function(dismis){

			var formData = new FormData();
			formData.append('file',$scope.file);
			formData.append('id', $scope.item.id);
			formData.append('title',$scope.item.title);
			if(op ==1){
				formData.append('status',1);
			}else if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}
			//formData.append('orderId',$scope.item.orderId);
			formData.append('typeId',$scope.item.typeStr);
			//formData.append('typeStr',$scope.item.type.text);
			formData.append('linkUrl',$scope.item.linkUrl);
			formData.append('summary',$scope.item.summary);
			formData.append('context',$scope.item.context);
			formData.append('category',"share");

		    appBase.doFormData("message",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.share", {}, { reload: true });
		    });
		}
	}

})();