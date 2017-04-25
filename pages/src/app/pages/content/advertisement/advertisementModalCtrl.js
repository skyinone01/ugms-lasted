/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.content.advertisement')
			.controller('advertisementModalCtrl', advertisementModalCtrl);

	/** @ngInject */
	function advertisementModalCtrl($scope, $uibModalInstance, modelId,op, fileReader, $filter,appBase,$state) {

		$scope.picture = $filter('appImage')('theme/no-photo.png');
		$scope.useables=[{'value':2,'text':'通过'},{'value':3,'text':'不通过'}];
		$scope.types=[{'value':0,'text':'内部广告'},{'value':1,'text':'外部广告'}];
		$scope.showApplyDetail =false;

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
				status:'',
				weight:'',
				contactName:'',
				contactPhone:'',
				applydetail:'',
				applypeole:'',
				type:'',
			};
		}else{

			appBase.doGet("banner/"+modelId,null,function(response){
				$scope.welcome=response.data;
				//$("#data_date").val(response.data.begin_date)
				for(var i =0;i<$scope.types.length;i++){
					if($scope.types[i].value == response.data.type){
						$scope.type = $scope.types[i];
					}
				}
				if( response.data.status==3 && op==2){
					$scope.showApplyDetail =true;
				}
				$scope.typeId = response.data.type;
				$scope.realDate =response.data.beginDate
				$scope.realendDate =response.data.endDate
				$scope.picmark = "mark"
			});
		}




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
				if(status==3){
					$scope.showApplyDetail =true;
				}else {
					$scope.showApplyDetail =false;
				}
			}
		}
		$scope.selectType = function(x){
			$scope.typeId = x.type.value;
		}
		$scope.saveOrUpdate = function(dismis){

			var formData = new FormData();
			formData.append('file',$scope.file);
			formData.append('id', $scope.welcome.id);
			formData.append('link', $scope.welcome.link);
			formData.append('title',$scope.welcome.title);
			formData.append('content',$scope.welcome.content);
			if($scope.welcome.weight !=null){
				formData.append('weight',$scope.welcome.weight);
			}
			formData.append('type',$scope.typeId);
			if($scope.welcome.applydetail !=null){
				formData.append('applyDetail',$scope.welcome.applydetail);
			}
			if($scope.welcome.contactName !=null){
				formData.append('contactName',$scope.welcome.contactName);
			}
			if($scope.welcome.contactPhone !=null){
				formData.append('contactPhone',$scope.welcome.contactPhone);
			}
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
			formData.append('isBanner',0);

			appBase.doFormData("banner",formData,function(response){
				appBase.bubMsg("保存成功");
				dismis;
				$state.go("content.advertisement", {}, { reload: true });
			});
		}
	}

})();