/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.userInstructions.instruction')
		.controller('instructionsModalCtrl', instructionsModalCtrl);

	/** @ngInject */
	function instructionsModalCtrl($scope, $uibModalInstance, modelId,op, $http,fileReader, $filter,appCommon,$state,appBase) {
//		debugger;
		console.log($state);
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
				$scope.picmark = "mark";
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
		$scope.showSubmit = function(){
			if(op == 3){
				return false;
			}else {
				return true
			}
		}
		$scope.getFile = function (file) {
			fileReader.readAsDataUrl(file, $scope)
				.then(function (result) {
					$scope.models.imgUrl = result;
					$scope.picmark="mark";
					$scope.models.content = file.name;
			});
			$scope.file = file;
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
							appBase.bubMsg("修改成功");
							$state.go("instructions.instruction", {}, { reload: true });
						}
						else{
							appBase.bubMsg("修改失败");
							$uibModalInstance.close($scope.link);
							$state.go("instructions.instruction", {}, { reload: true });
						}
					});
			}
			else {
				$http.post(apiPath+'/instructions/save', $scope.models)
					.then(function (response) {
						if (response.data.error == 'succ') {
							appBase.bubMsg("保存成功");
							$uibModalInstance.close($scope.link);
							$state.go("instructions.instruction", {}, { reload: true });
						}
						else {
							appBase.bubMsg("保存失败");
							$uibModalInstance.close($scope.link);
							$state.go("instructions.instruction", {}, { reload: true });
						}
					});
			}
		});

		if (modelId) {
//			debugger;
			$http.get(apiPath+'/instructions/' + modelId)
				.then(function (response) {
//					alert(response.data.pid);
					$scope.models.imgUrl = response.data.imgUrl;
					$scope.models.title = response.data.title;
					$scope.models.order = response.data.order;
					$scope.models.id = response.data.id;
					$scope.models.pid = response.data.pid;
					$scope.models.content = response.data.imgUrl;
//					$scope.names = ["Google", "Runoob", "Taobao"];
				});
		}
	}

})();