/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userInstructions.instruction')
		.controller('instructionsModalCtrl', instructionsModalCtrl);

	/** @ngInject */
	function instructionsModalCtrl($scope, $uibModalInstance, modelId, $http,fileReader, $filter,appCommon) {
		debugger;
		var localPath = appCommon.autoCompleteUrl();//'http://localhost:8080';
		var apiPath = localPath.substring(0, localPath.lastIndexOf('/'));
		$.get(apiPath+'/instructions/', function (response) {
			$scope.smartTableData = [];
			$scope.smartTableData.push({
				id:0,
				title: "无父标题"
			})
			response.data.items.forEach(function (value, index, array) {
				$scope.smartTableData.push({
					id: value.id,
					title: value.serialnumber+value.title
				})
			});

		});
		$scope.pathApi=apiPath;
		$scope.picture = $filter('appImage')('theme/no-photo.png');

		$scope.removePicture = function () {
			$scope.models.imgUrl = "";
			$scope.picture = $filter('appImage')('theme/no-photo.png');
			$scope.noPicture = true;
		};

		$scope.uploadPicture = function () {
			var fileInput = document.getElementById('uploadFile');
			fileInput.click();
		};

		$scope.imgUpload = function(files) {

			var data = new FormData();      //以下为像后台提交图片数据
			data.append('image', files[0]);
			$http({
				method: 'post',
				url: apiPath+'/upload/pictrues/instructions',
				data:data,
				headers: {'Content-Type': undefined},
				transformRequest: angular.identity
			}).success(function(data) {
				if (data.result_code == 'SUCCESS') {
					$scope.models.imgUrl = data.result_value;
				}
				if(data.result_code == 'FAIL'){
					console.log(data)
				}
			})
		};

		$scope.getFile = function () {
			fileReader.readAsDataUrl($scope.file, $scope)
				.then(function (result) {
					$scope.picture = result;
				});
		};

		$scope.models = {
			id: modelId,
			order: 0,
			title: '',
			imgUrl: '',
			pid: '',
			type: 'instructions'
		};


		$scope.link = '';
		$scope.ok = function () {
			$uibModalInstance.close($scope.link);
		};

		$scope.save = (function () {
			if (modelId) {
				$http.post(apiPath+'/instructions/' + modelId, $scope.models)
					.then(function (response) {
						if (response.data.error == 'succ') {
							$uibModalInstance.close($scope.link);
							window.location.reload();
						}
						else alert('失败');
					});
			}
			else {
				$http.post(apiPath+'/instructions/save', $scope.models)
					.then(function (response) {
						if (response.data.error == 'succ') {
							$uibModalInstance.close($scope.link);
							window.location.reload();
						}
						else alert('失败');
					});
			}
		});

		if (modelId) {
			$http.get(apiPath+'/instructions/' + modelId)
				.then(function (response) {
					$scope.models.imgUrl = response.data.imgUrl;
					$scope.models.title = response.data.title;
					$scope.models.order = response.data.order;
					$scope.models.id = response.data.id;
					$scope.models.pid = response.data.pid;
				});
		}
	}

})();