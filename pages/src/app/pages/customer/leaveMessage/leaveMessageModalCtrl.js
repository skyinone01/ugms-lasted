/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.customer.leaveMessage')
		.controller('LeaveMessageModalCtrl', LeaveMessageModalCtrl);

	/** @ngInject */
	function LeaveMessageModalCtrl($scope, $uibModalInstance, modelId, name, fileReader, $filter,appBase,$state) {

		$scope.item = {
     			id: 0,
     			content: '',
     			replayContent: ''
     	};
		 if(modelId){
		     appBase.doGet("leaveMessage/"+modelId,null,function(response){
                 $scope.item=response.data;
		     });
		 }

		 $scope.showSubmit = function(){
			if(name == 2){
				return false;
			}else {
				return true
			}
		};
		 
		$scope.save = function(dismis){
			var formData = new FormData();
			formData.append('id', $scope.item.id);
			formData.append('replayContent',$scope.item.replayContent);

		    appBase.doFormData("leaveMessage",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("customer.leaveMessage", {}, { reload: true });
		    });
		}
	}

})();