(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DotXhDetailController', DotXhDetailController);

    DotXhDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DotXh'];

    function DotXhDetailController($scope, $rootScope, $stateParams, previousState, entity, DotXh) {
        var vm = this;

        vm.dotXh = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:dotXhUpdate', function(event, result) {
            vm.dotXh = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
