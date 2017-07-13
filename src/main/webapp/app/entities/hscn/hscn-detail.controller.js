(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('HscnDetailController', HscnDetailController);

    HscnDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Hscn'];

    function HscnDetailController($scope, $rootScope, $stateParams, previousState, entity, Hscn) {
        var vm = this;

        vm.hscn = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:hscnUpdate', function(event, result) {
            vm.hscn = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
