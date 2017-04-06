/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.message')
		.controller('messageModalCtrl', messageModalCtrl);

	/** @ngInject */
	function messageModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {

		 if(modelId == 0){
		    $scope.item = {
         			id: 0,
         			title: '',
         			url: '',
         			useable: '',
         			beginDate: '',
         			endDate: '',
         			orders: '',
         			status:''
         	};
		 }else{
		     appBase.doGet("message/"+modelId,null,function(response){
                 $scope.item=response.data;
				 $("#data_date").val(response.data.begin_date)
				 $scope.realDate =response.data.beginDate
				 $scope.realEndDate =response.data.endDate
				 $scope.picmark = "mark"
		     });
		 }

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':2,'text':'通过'},{'value':3,'text':'不通过'}]

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

		$scope.setDate = function(){
			$scope.realDate = $("#data_id").val();
			//$("#data_date").val($("#data_id").val());
		}
        $scope.setEndDate = function(){
			$scope.realEndDate = $("#data_endId").val();
			//$("#data_date").val($("#data_id").val());
		}
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
		$scope.saveOrUpdate = function(dismis){

		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.item.id);
		    formData.append('link', $scope.item.link);
		    formData.append('title',$scope.item.title);
			if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}
		    formData.append('useable',1);
		    formData.append('beginDate',$("#data_id").val());
		    formData.append('endDate',$("#data_endId").val());
		    formData.append('orders',$scope.item.orders);

		    appBase.doFormData("message",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.message", {}, { reload: true });
		    });
		}
	}

})();