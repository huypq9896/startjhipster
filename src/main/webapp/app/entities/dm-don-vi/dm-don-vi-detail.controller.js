(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DmDonViDetailController', DmDonViDetailController);

    DmDonViDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DmDonVi'];

    function DmDonViDetailController($scope, $rootScope, $stateParams, previousState, entity, DmDonVi) {
        var vm = this;

        vm.dmDonVi = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('appsmysqlApp:dmDonViUpdate', function(event, result) {
            vm.dmDonVi = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
