/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.welcome')
		.controller('welcomeModalCtrl', welcomeModalCtrl);

	/** @ngInject */
	function welcomeModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {

		 if(modelId == 0){
		    $scope.welcome = {
         			id: 0,
         			title: '',
         			url: '',
         			useable: '',
         			begin_date: '',
         			orders: '',
         			status:''
         	};
		 }else{
		     appBase.doGet("welcome/"+modelId,null,function(response){
                $scope.welcome=response.data;
				 $scope.picmark = "mark"
		     });
		 }

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':1,'text':'启用'},{'value':2,'text':'禁用'}]
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
			if(op =="detail"){
				return false;
			}else {
				return true
			}
		}
		$scope.getFile = function (file) {
			fileReader.readAsDataUrl(file, $scope)
				.then(function (result) {
					$scope.welcome.picture = result;
					$scope.picmark="mark";

			});
			$scope.file = file;
		};


		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};

		$scope.saveOrUpdate = function(dismis){

		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.welcome.id);
		    formData.append('title',$scope.welcome.title);
		    formData.append('useable',$scope.welcome.useable);
		    formData.append('begin_date',$("#data_id").val());
		    formData.append('orders',$scope.welcome.orders);

		    appBase.doFormData("welcome",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.welcome", {}, { reload: true });
		    });
		}
	}

})();