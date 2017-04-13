/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.banner')
		.controller('bannerModalCtrl', bannerModalCtrl);

	/** @ngInject */
	function bannerModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {

		 if(modelId == 0){
		    $scope.welcome = {
         			id: 0,
         			title: '',
         			url: '',
         			useable: '',
				    beginDate: '',
         			endDate: '',
         			orders: '',
         			link: '',
         			status:''
         	};
		 }else{

		     appBase.doGet("banner/"+modelId,null,function(response){
                 $scope.welcome=response.data;
				 //$("#data_date").val(response.data.begin_date)
				 $scope.realDate =response.data.beginDate
				 $scope.realendDate =response.data.endDate
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
					$scope.welcome.picture = result;
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
			$scope.realendDate = $("#data_endid").val();
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
			var status = $scope.welcome.status;
			if (status ==2 || status ==3){
				$scope.applyStatus = status;
			}
		}
		$scope.saveOrUpdate = function(dismis){

		    var formData = new FormData();
		    formData.append('file',$scope.file);
		    formData.append('id', $scope.welcome.id);
		    formData.append('link', $scope.welcome.link);
		    formData.append('title',$scope.welcome.title);
			if ($scope.applyStatus != null){
				formData.append('status',$scope.applyStatus);
			}
			if ($scope.realDate != null){
				formData.append('beginDate',$scope.realDate );
			}
			if ($scope.realendDate != null){
				formData.append('endDate',$scope.realendDate);
			}
		    formData.append('useable',1);
		    formData.append('orderId',$scope.welcome.orderId);
		    formData.append('isBanner',1);

		    appBase.doFormData("banner",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.banner", {}, { reload: true });
		    });
		}
	}

})();