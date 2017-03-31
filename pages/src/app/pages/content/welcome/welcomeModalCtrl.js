/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.welcome')
		.controller('welcomeModalCtrl', welcomeModalCtrl);

	/** @ngInject */
	function welcomeModalCtrl($scope, $uibModalInstance, modelId, fileReader, $filter,appBase,callback) {

		 if(modelId == null){
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
		     });
		 }

		$scope.picture = $filter('appImage')('theme/no-photo.png');

		$scope.removePicture = function () {
			$scope.picture = $filter('appImage')('theme/no-photo.png');
			$scope.noPicture = true;
		};

		$scope.uploadPicture = function () {
			var fileInput = document.getElementById('uploadFile');
			fileInput.click();
		};

		$scope.getFile = function (file) {
			fileReader.readAsDataUrl(file, $scope)
				.then(function (result) {
					$scope.welcome.picture = result;
					$scope.mark="mark";

			});
			$scope.file = file;
		};


		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};

		$scope.saveOrUpdate = function(){

		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.welcome.id);
		    formData.append('title',$scope.welcome.title);
		    formData.append('useable',$scope.welcome.useable);
		    formData.append('begin_date',$("#data_id").val());
		    formData.append('orders',$scope.welcome.orders);
//		    formData.append('status',$scope.welcome.status);

		    appBase.doFormData("welcome",formData,function(response){
		        appBase.bubMsg("保存成功");
		        callback
//		        window.location.reload();
		    });
		}
	}

})();