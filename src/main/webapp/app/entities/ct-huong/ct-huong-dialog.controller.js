(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('CtHuongDialogController', CtHuongDialogController);

    CtHuongDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'CtHuong'];

    function CtHuongDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, CtHuong) {
        var vm = this;

        vm.ctHuong = entity;
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
            if (vm.ctHuong.id !== null) {
                CtHuong.update(vm.ctHuong, onSaveSuccess, onSaveError);
            } else {
                CtHuong.save(vm.ctHuong, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('appsmysqlApp:ctHuongUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.ngayDuyet = false;
        vm.datePickerOpenStatus.tuNgay = false;
        vm.datePickerOpenStatus.denNgay = false;
        vm.datePickerOpenStatus.tuNgayH = false;
        vm.datePickerOpenStatus.denNgayH = false;
        vm.datePickerOpenStatus.ngay1 = false;
        vm.datePickerOpenStatus.ngayNuoi = false;
        vm.datePickerOpenStatus.tuNgayBs = false;
        vm.datePickerOpenStatus.denNgayBs = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
