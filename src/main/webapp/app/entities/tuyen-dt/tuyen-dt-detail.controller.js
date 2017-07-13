(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('TuyenDtDetailController', TuyenDtDetailController);

    TuyenDtDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'TuyenDt'];

    function TuyenDtDetailController($scope, $rootScope, $stateParams, previousState, entity, TuyenDt) {
        var vm = this;

        vm.tuyenDt = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:tuyenDtUpdate', function(event, result) {
            vm.tuyenDt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
