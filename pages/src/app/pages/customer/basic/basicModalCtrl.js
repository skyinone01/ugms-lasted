/**
 * @author roy
 * created on 05.04.2017
 */
(function () {
	'use strict';

	angular.module('BlurAdmin.pages.customer.basic')
		.controller('BasicModalCtrl', BasicModalCtrl);

	/** @ngInject */
	function BasicModalCtrl($scope, $uibModalInstance, modelId, $http,fileReader, $filter, appBase,$state) {

		 
		    $scope.item = {
     			userId: 0,
     			userName: '',
     			userMobile: '',
     			userSex: '',
     			userBirthday: '',
     			userHeight: '',
     			userSleep: '',
     			userExperience: '',
     			userCreateTime: ''
         	};
		    if(modelId){
		     appBase.doGet("basic/"+modelId,null,function(response){
		    	 $scope.item = response.data;
		    	 $scope.realDate =response.data.userBirthday;
                 /*$scope.item.userName=response.data.username;
                 $scope.item.userMobile=response.data.userMobile;
                 $scope.item.userMobile=response.data.userMobile;
                 $scope.item.userSex=response.data.userSex;
                 $scope.item.userBirthday=response.data.userBirthday;
                 $scope.item.userHeight=response.data.userHeight;
                 $scope.item.userSleep=response.data.userSleep;
                 $scope.item.userExperience=response.data.userExperience;
                 $scope.item.userCreateTime=response.data.userCreateTime;*/
		     });
		 };

		 $scope.setDate = function(){
				$scope.realDate = $("#data_id").val();
		};
		$scope.userSleepList=["1","2","3","4","5","6","7","8","9","10"];
			
			
			/*[{code:'1',text:'1'},
		                      {code:'2',text:'2'},
		                      {code:'3',text:'3'},
		                      {code:'4',text:'4'},
		                      {code:'5',text:'5'},
		                      {code:'6',text:'6'},
		                      {code:'7',text:'7'},
		                      {code:'8',text:'8'},
		                      {code:'9',text:'9'},
		                      {code:'10',text:'10'}];*/
		
		/*$scope.setApplyType = function(){
			$scope.userSleep = $scope.item.userSleep;
		}*/
			
		
		$scope.saveOrUpdate = function(dismis){

			var formData = new FormData();
			formData.append('userId', $scope.item.userId);
			formData.append('userName',$scope.item.userName);
			formData.append('userMobile',$scope.item.userMobile);
			formData.append('userSex',$scope.item.userSex);
			if ($scope.realDate != null){
				formData.append('userBirthday',$scope.realDate );
			}
//			formData.append('userBirthday',$scope.item.userBirthday);
			formData.append('userHeight',$scope.item.userHeight);
			formData.append('userSleep',$scope.item.userSleep);
			formData.append('userExperience',$scope.item.userExperience);

		    appBase.doFormData("basic",formData,function(response){
		        appBase.bubMsg("保存成功");
				dismis;
				$state.go("customer.basic", {}, { reload: true });
		    });
		}
	}

})();