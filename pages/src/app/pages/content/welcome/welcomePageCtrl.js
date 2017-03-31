/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.welcome')
        .controller('WelcomePageCtrl', WelcomePageCtrl);

    /** @ngInject */
    function WelcomePageCtrl($scope,$uibModal, baProgressModal, $filter,appBase) {

        $scope.listWelcome = function(){
                appBase.doGet("welcome","{'page':0,'size':20}",function(response){
        		     if(response.data != null){
        		         $scope.items = response.data.items;
        		     }
        		})
        }


        $scope.pageNum = 1;
		$scope.searchNameValue = '';
		$scope.listWelcome();


        $scope.showButton = function(index,name){
            if(name == 'edit'){
                var s = $scope.items[index].status;
                if(s==4 || s==5){
                    return false;
                }
            }
            return true;
        }
        $scope.showButtonName = function(index){
            switch($scope.items[index].status){
                case 1:
                    return "审核";
                case 2:
                    return "审核";
                case 3:
                     return "审核";
                case 4:
                    return "发布";
                case 5:
                    return "停用";
                case 6:
                     return "启用";

            }
        }

        $scope.statusEmu = ["草稿" ,"待审核","审核未通过","审核通过","已发布","已作废"];
		$scope.showStatus = function(status){
             return $scope.statusEmu[status-1];
		}

		$scope.deleteOne = function(id){
            appBase.doDelete("welcome/"+id,null,function(res){
                appBase.bubMsg("删除成功");
            });
		}

		$scope.open = function (page, size, id) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'welcomeModalCtrl',
				resolve: {
					modelId: id,
					callback:$scope.listWelcome
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;

		$scope.btnNext = (function (pageNum) {
			var offset = 15 * pageNum;
			$http.get('http://120.26.88.27:8080/youge-backend/showMessage/' + offset + '/15?YGType=1').then(function (response) {
				if (response.data.lastPage > pageNum) {
					$scope.smartTableData = [];
				}
				else {
					return true;
				}
				response.data.list.forEach(function (value) {
					$scope.smartTableData.push({
						YGID: value.ygid,
						YGTitle: value.ygtitle,
						YGCreateDate: value.ygcreateDate
					})
				});
				$scope.pageNum = pageNum + 1;
			});
		});

		$scope.btnUp = (function (pageNum) {
			var offset = 15 * (pageNum - 1);
			$http.get('http://120.26.88.27:8080/youge-backend/showMessage/' + offset + '/15?YGType=1').then(function (response) {
				if (response.data.hasNextPage) {
					if (offset < 0) {
						return true;
					}
					$scope.smartTableData = [];
				}
				else {
					return true;
				}
				response.data.list.forEach(function (value) {
					$scope.smartTableData.push({
						YGID: value.ygid,
						YGTitle: value.ygtitle,
						YGCreateDate: value.ygcreateDate
					})
				});
				$scope.pageNum = pageNum == 0 ? 1 : pageNum;
			});
		});


    }

})();
