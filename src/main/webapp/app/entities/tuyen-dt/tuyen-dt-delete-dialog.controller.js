(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('TuyenDtDeleteController',TuyenDtDeleteController);

    TuyenDtDeleteController.$inject = ['$uibModalInstance', 'entity', 'TuyenDt'];

    function TuyenDtDeleteController($uibModalInstance, entity, TuyenDt) {
        var vm = this;

        vm.tuyenDt = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            TuyenDt.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
