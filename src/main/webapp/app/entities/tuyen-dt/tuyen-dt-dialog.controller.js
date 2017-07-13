(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('TuyenDtDialogController', TuyenDtDialogController);

    TuyenDtDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'TuyenDt'];

    function TuyenDtDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, TuyenDt) {
        var vm = this;

        vm.tuyenDt = entity;
        vm.clear = clear;
        vm.save = save;
        vm.listQ = [{
        	value: 1,
        	text: 'Nam'
        },{
        	value: 0,
        	text: 'Nữ'
        }];
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.tuyenDt.id !== null) {
                TuyenDt.update(vm.tuyenDt, onSaveSuccess, onSaveError);
            } else {
                TuyenDt.save(vm.tuyenDt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:tuyenDtUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
