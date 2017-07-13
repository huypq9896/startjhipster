(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DmDonViDialogController', DmDonViDialogController);

    DmDonViDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DmDonVi'];

    function DmDonViDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DmDonVi) {
        var vm = this;

        vm.dmDonVi = entity;
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
            if (vm.dmDonVi.id !== null) {
                DmDonVi.update(vm.dmDonVi, onSaveSuccess, onSaveError);
            } else {
                DmDonVi.save(vm.dmDonVi, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:dmDonViUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
