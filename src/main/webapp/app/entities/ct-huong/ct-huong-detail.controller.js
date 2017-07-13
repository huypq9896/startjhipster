(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('CtHuongDetailController', CtHuongDetailController);

    CtHuongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CtHuong'];

    function CtHuongDetailController($scope, $rootScope, $stateParams, previousState, entity, CtHuong) {
        var vm = this;

        vm.ctHuong = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:ctHuongUpdate', function(event, result) {
            vm.ctHuong = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
