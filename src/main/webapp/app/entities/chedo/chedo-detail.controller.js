(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('ChedoDetailController', ChedoDetailController);

    ChedoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Chedo'];

    function ChedoDetailController($scope, $rootScope, $stateParams, previousState, entity, Chedo) {
        var vm = this;

        vm.chedo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:chedoUpdate', function(event, result) {
            vm.chedo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
