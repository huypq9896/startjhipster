(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('LuongDetailController', LuongDetailController);

    LuongDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Luong'];

    function LuongDetailController($scope, $rootScope, $stateParams, previousState, entity, Luong) {
        var vm = this;

        vm.luong = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:luongUpdate', function(event, result) {
            vm.luong = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
