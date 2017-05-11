/**
 * @author a.demeshko
 * created on 21.01.2016
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.article.column')
			.controller('columnModalCtrl', columnModalCtrl);

	/** @ngInject */
	function columnModalCtrl($scope,$stateParams, fileReader, $filter,appBase,$state) {

		var modelId= $stateParams.modelId,op=$stateParams.op;
		modelId = 0;
		$scope.showApplyDetail =false;

		if(modelId == 0){
			$scope.item = {
				id: 0,
				title: '',
				url: '',
				paymode: true,
				payitem1:{price:0,month:0},
				payitem2:{price:0,month:0},
				payitem3:{price:0,month:0},
				link: '',
				articles:[],
				aplydetail:'',
				applypeole:''
			};
		}else{

			appBase.doGet("articleColumn/"+modelId,null,function(response){
				$scope.item=response.data;
				if( response.data.status==3 && op==2){
					$scope.showApplyDetail =true;
				}
				$scope.picmark = "mark"
			});
		}

		$scope.showSubmit = function(){
			if(op ==2){
				return false;
			}else {
				return true
			}
		}
		$scope.months=[{'value':1,'text':'1个月'},{'value':2,'text':'2个月'},{'value':3,'text':'3个月'},{'value':4,'text':'4个月'},
			  {'value':5,'text':'5个月'},{'value':6,'text':'6个月'},{'value':7,'text':'7个月'},{'value':8,'text':'8个月'},
			 {'value':9,'text':'9个月'},{'value':10,'text':'10个月'},{'value':11,'text':'11个月'},{'value':12,'text':'12个月'}]

		$scope.removeLabel = function (id) {
			//$scope.picture = $filter('appImage')('theme/no-photo.png');
			//$scope.noPicture = true;
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