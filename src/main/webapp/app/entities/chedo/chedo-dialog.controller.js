(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('ChedoDialogController', ChedoDialogController);

    ChedoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Chedo'];

    function ChedoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Chedo) {
        var vm = this;

        vm.chedo = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.chedo.id !== null) {
                Chedo.update(vm.chedo, onSaveSuccess, onSaveError);
            } else {
                Chedo.save(vm.chedo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:chedoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
