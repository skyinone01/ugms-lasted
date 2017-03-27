/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.guide')
		.controller('guideModalCtrl', guideModalCtrl);

	/** @ngInject */
	function guideModalCtrl($scope, $uibModalInstance, modelId, $http, fileReader, $filter) {
		$scope.picture = $filter('appImage')('theme/no-photo.png');

		$scope.removePicture = function () {
			$scope.picture = $filter('appImage')('theme/no-photo.png');
			$scope.noPicture = true;
		};

		$scope.uploadPicture = function () {
			var fileInput = document.getElementById('uploadFile');
			fileInput.click();

		};

		$scope.getFile = function () {
			fileReader.readAsDataUrl($scope.file, $scope)
				.then(function (result) {
					$scope.picture = result;
				});
		};

		$scope.models = {
			ygid: modelId,
			ygtype: 1,
			ygtitle: '',
			ygurl: '',
			ygflag: 0,
			ygcreateDate: Date.parse(new Date()),
			ygimgBig: ''
		};

		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};

		$scope.save = (function () {
			if (modelId) {
				$http.put('http://120.26.88.27:8080/youge-backend/showMessage/' + modelId, $scope.models)
					.then(function (response) {
						if (response.data == 1) {
							$uibModalInstance.close($scope.link);
							window.location.reload();
						}
						else alert('失败');
					});
			}
			else {
				$http.post('http://120.26.88.27:8080/youge-backend/showMessage/', $scope.models)
					.then(function (response) {
						if (response.data == 1) {
							$uibModalInstance.close($scope.link);
							window.location.reload();
						}
						else alert('失败');
					});
			}
		});

		if (modelId) {
			$http.get('http://120.26.88.27:8080/youge-backend/showMessage/' + modelId)
				.then(function (response) {
					$scope.models.ygid = response.data.ygid;
					$scope.models.ygtype = response.data.ygtype;
					$scope.models.ygtitle = response.data.ygtitle;
					$scope.models.ygurl = response.data.ygurl;
					$scope.models.ygimgBig = response.data.ygimgBig;
				});
		}
	}

})();