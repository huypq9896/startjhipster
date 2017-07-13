(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('LuongDialogController', LuongDialogController);

    LuongDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Luong'];

    function LuongDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Luong) {
        var vm = this;

        vm.luong = entity;
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
            if (vm.luong.id !== null) {
                Luong.update(vm.luong, onSaveSuccess, onSaveError);
            } else {
                Luong.save(vm.luong, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:luongUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
