(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DotXhDialogController', DotXhDialogController);

    DotXhDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DotXh'];

    function DotXhDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DotXh) {
        var vm = this;

        vm.dotXh = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dotXh.id !== null) {
                DotXh.update(vm.dotXh, onSaveSuccess, onSaveError);
            } else {
                DotXh.save(vm.dotXh, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:dotXhUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.ngayCt = false;
        vm.datePickerOpenStatus.lastCode = false;
        vm.datePickerOpenStatus.ngayKhoa = false;
        vm.datePickerOpenStatus.ngay1 = false;
        vm.datePickerOpenStatus.ngay2 = false;
        vm.datePickerOpenStatus.ngay3 = false;
        vm.datePickerOpenStatus.ngayDuyet = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
