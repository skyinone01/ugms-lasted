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
					$scope.picture = result;
					$scope.file = file;
			});
		};

		$scope.welcome = {
			id: modelId,
			title: '',
			url: '',
			flag: 0,
			sort: 0,
			imgBig: ''
		};

		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};
		$scope.save = (function () {
		    appBase.dePost("welcome",$scope.welcome,function(response){
		        appBase.bubMsg("保存成功");
		        window.location.reload();
		    });
		});

		if (modelId) {
		    appBase.doGet("welcome/"+modelId,null,function(response){
                    $scope.welcome.id = response.data.id;
//					$scope.welcome.ygtype = response.data.ygtype;
					$scope.welcome.title = response.data.title;
					$scope.welcome.url = response.data.url;
					$scope.welcome.imgBig = response.data.imgBig;
		    });
		}


	}

})();