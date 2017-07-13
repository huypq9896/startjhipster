(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('HscnDialogController', HscnDialogController);

    HscnDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Hscn'];

    function HscnDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Hscn) {
        var vm = this;

        vm.hscn = entity;
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
            if (vm.hscn.id !== null) {
                Hscn.update(vm.hscn, onSaveSuccess, onSaveError);
            } else {
                Hscn.save(vm.hscn, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:hscnUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
