/**
 * Created by n.poltoratsky
 * on 23.06.2016.
 */
(function(){
    'use strict';

    angular.module('BlurAdmin.pages.common')
        .controller('datePickerPopupCtrl', datePickerPopupCtrl);

    /** @ngInject */
    function datePickerPopupCtrl($scope) {

        $scope.open = open;
        $scope.opened = false;
        $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
        $scope.format = $scope.formats[1];
        $scope.options = {
            showWeeks: false
        };

        function open() {
            $scope.opened = true;
        }
    }
})();