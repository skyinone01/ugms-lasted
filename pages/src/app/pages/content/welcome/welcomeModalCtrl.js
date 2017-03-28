/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.welcome')
		.controller('welcomeModalCtrl', welcomeModalCtrl);

	/** @ngInject */
	function welcomeModalCtrl($scope, $uibModalInstance, modelId, $http, fileReader, $filter,appBase) {

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
					$scope.welcome.url = result;
			});
			$scope.file = file;
		};

		$scope.welcome = {
			id: modelId,
			title: '',
			url: '',
			useable: 0,
			begin_date: '',
			orders: ''
		};
		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};

		$scope.saveOrUpdate = function(){
		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id',modelId);
		    formData.append('title',$scope.file);
		    formData.append('useable',$scope.useable);
		    formData.append('begin_date',$scope.begin_date);
		    formData.append('orders',$scope.orders);

		    appBase.doFormData("welcome",formData,function(response){
		        appBase.bubMsg("保存成功");
		        window.location.reload();
		    });
		}
	}

})();