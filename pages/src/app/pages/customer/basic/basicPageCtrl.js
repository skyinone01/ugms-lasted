/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.customer.basic')
        .controller('BasicPageCtrl', BasicPageCtrl);

    /** @ngInject */
    function BasicPageCtrl($scope,$uibModal,$http, baProgressModal, $filter,appBase,appCommon) {
    	var localPath = appCommon.autoCompleteUrl();//'http://localhost:8080';
		var apiPath = localPath.substring(0, localPath.lastIndexOf('/'));

        $scope.perPage = 20;
        $scope.page = 1;
        $scope.listItem = function(){
        	var userName = $("#searchNameValue").val();
			var param = "page="+$scope.page+"&perPage="+$scope.perPage;
			if(userName!=""&& userName!=undefined){
				param = param + "&userName="+userName;
			}else{
				param = param + "&userName="+null;
			}
			appBase.doGet("basic?"+param,null,function(response){
        		     if(response.data != null){
        		         $scope.items = response.data.items;
        		         $scope.totalPage = Math.ceil(response.data.total_count/$scope.perPage);
        		     }
        		})
        }
		$scope.listItem();
		
		//1正常
		$scope.showButtonStop = function(index,status){
            if(status == false){
            	return false;
            }
            return true;
        }
		//1正常
		$scope.showButtonRePlay = function(index,status){
            if(status == true){
            	return false;
            }
            return true;
        }

		$scope.open = function (page, size, id) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'BasicModalCtrl',
				resolve: {
					modelId: id,
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;
		
		
		$scope.updateStatus = function (id, status) {
			var result = "";
			if(status == 0){
				result = confirm('确认停止用户信息？');
			}else{
				result = confirm('确认恢复用户信息？');
			}
			
			if(!result){
				return;
			}
			var param = "userId="+id+"&status="+status;
            appBase.doPost("basic/updateStatus?"+param,null,function(res){
                appBase.bubMsg("操作成功");
				$scope.listItem();
            });
		};
		
		$scope.sendSMS = function (id) {
			var param = "userId="+id;
			appBase.doPost("basic/sendSMS4ResetPwd?"+param,null,function(res){
                appBase.bubMsg("操作成功");
				$scope.listItem();
            });
		}
		
		/*$scope.selectUser = function (userName) {
			appBase.doGet("basic/selectUser?"+param,null,function(res){
                appBase.bubMsg("操作成功");
				$scope.listItem();
            });
		}*/
		
		$scope.exportUserStats = function () {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			var userName = $("#searchNameValue").val();
			
			var param = "";
			if(userName!=""&& userName!=undefined){
				param = param + "&userName="+userName;
			}else{
				param = param + "&userName="+null;
			}
			
			/*if(startDate!=""&& startDate!=undefined){
				param = param + "&startDate="+startDate;
			}else{
				param = param + "&startDate="+null;
			}
			if(endDate!=""&& endDate!=undefined){
				param = param + "&endDate="+endDate;
			}else{
				param = param + "&endDate="+null;
			}*/
			
			window.location.href=apiPath+'/basic/exportUserStats?'+param;
			
		}

        $scope.upAble = function(page){
            if(page == 1){
                return true;
            }
            return false;
        }

        $scope.nextAble = function(page){
            if(page == $scope.totalPage){
                return true;
            }
            return false;
        }

		$scope.btnNext = function(){
		    $scope.page = $scope.page+1;
		    $scope.listItem();
		};

		$scope.btnUp = function () {
			$scope.page = $scope.page-1;
            $scope.listItem();
		};

    }

})();
