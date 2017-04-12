/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.description')
		.controller('descriptionModalCtrl', descriptionModalCtrl);

	/** @ngInject */
	function descriptionModalCtrl($scope, $uibModalInstance, modelId,op,type, fileReader, $filter,appBase,$state) {

		 appBase.doGet("description/layout",null,function(response){
			$scope.layouts=response.data;
			 if(modelId == 0){
				 $scope.typeStr = "attention";
				 if (type==1){
					 type="说明页";
					 $scope.typeStr = "explain";
				 }

				 $scope.item = {
					 id: 0,
					 title: '',
					 url: '',
					 useable: '',
					 type: type,
					 content: '',
					 layoutCode: '',
					 status:''
				 };
			 }else{
				 appBase.doGet("description/"+modelId,null,function(response){
					 $scope.item=response.data;
					 for(var i =0;i<$scope.layouts.length;i++){
						 if($scope.layouts[i].code == response.data.layoutCode){
							 $scope.layout = $scope.layouts[i];
						 }
					 }
					 $scope.typeStr = $scope.item.type;
					 $scope.item.type="说明页";
					 if ($scope.item.type=="attention"){
						 $scope.item.type="注意事项";
					 }
					 $scope.picmark = "mark";
					 if($scope.item.useable){
						 $scope.item.useable=1;
					 }else {
						 $scope.item.useable=0;
					 }
				 });
			 }
		 });

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':2,'text':'通过'},{'value':3,'text':'不通过'}]
		$scope.use=[{'value':1,'text':'启用'},{'value':0,'text':'禁用'}]

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
		$scope.setApplyLayout = function(x){
			$scope.layoutCode = x.layout.code;
		}
		$scope.saveOrUpdate = function(dismis){

		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.item.id);
		    formData.append('title',$scope.item.title);
			if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}
		    formData.append('useable',$scope.item.useable);
		    formData.append('content',$scope.item.content);
		    formData.append('type',$scope.typeStr);
			if($scope.layoutCode!=null){
				formData.append('layoutCode',$scope.layoutCode);
			}
			appBase.doFormData("description",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.description", {}, { reload: true });
		    });
		}
	}

})();