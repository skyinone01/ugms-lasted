/**
 * @author roy
 */
(function () {
    'use strict';

    angular.module('BlurAdmin.pages.content.welcome')
        .controller('WelcomePageCtrl', WelcomePageCtrl);

    /** @ngInject */
    function WelcomePageCtrl($scope,$uibModal, baProgressModal, $filter,appBase) {

        $scope.listItem = function(){
                appBase.doGet("welcome","{'page':0,'size':20}",function(response){
        		     if(response.data != null){
        		         $scope.items = response.data.items;
        		     }
        		})
        }


        $scope.pageNum = 1;
		$scope.searchNameValue = '';
		$scope.listItem();


        $scope.showButton = function(index,name){
            if(name == 'edit'){
                var s = $scope.items[index].status;
                if(s==4 || s==5){
                    return false;
                }
            }
            return true;
        }

		$scope.deleteOne = function(id){
			var result = confirm('确认删除！');
			if(!result){
				return;
			}
            appBase.doDelete("welcome/"+id,null,function(res){
                appBase.bubMsg("删除成功");
				$scope.listItem();
            });
		}

		//op 1 新增 2详情 3编辑 4 审核
		$scope.open = function (page, size, id,opstr) {
			$uibModal.open({
				animation: true,
				templateUrl: page,
				size: size,
				controller: 'welcomeModalCtrl',
				resolve: {
					modelId: id,
					op: opstr,
				}
			});
		};
		$scope.openProgressDialog = baProgressModal.open;

		$scope.statusEmu = ["待审核","审批通过","审批未通过","已发布","已作废"];
		$scope.showStatus = function(status){
			return $scope.statusEmu[status-1];
		}

		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.showButtonName = function(index){
			switch($scope.items[index].status){
				case 1:
					return "审核";
				case 2:
					return "发布";
				case 3:
					return "审核";
				case 4:
					return "停用";
				case 5:
					return "启用";
			}
		}
		//审核 1 发布 2 启用 3 停用 4
		//1待审批、2审批通过、3审批不通过、4已发布、5已作作废】
		$scope.operation = function(name,id){
			var op=0;
			switch(name){
				case "审核":
					$scope.open('app/pages/content/welcome/welcomeModal.html', 'lg',id,4)
					return;
				case "发布":
					op=4;
					break;
				case "启用":
					op=4;
					break;
				case "停用":
					op=2;
					break;
			}

			appBase.doPut("welcome/"+id+"?op="+op,null,function(response){
				appBase.bubMsg(name+"成功");
				$scope.listItem();
			});
		}

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
