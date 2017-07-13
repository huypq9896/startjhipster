(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('CtHuongDeleteController',CtHuongDeleteController);

    CtHuongDeleteController.$inject = ['$uibModalInstance', 'entity', 'CtHuong'];

    function CtHuongDeleteController($uibModalInstance, entity, CtHuong) {
        var vm = this;

        vm.ctHuong = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            CtHuong.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
