(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('LuongDeleteController',LuongDeleteController);

    LuongDeleteController.$inject = ['$uibModalInstance', 'entity', 'Luong'];

    function LuongDeleteController($uibModalInstance, entity, Luong) {
        var vm = this;

        vm.luong = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Luong.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
