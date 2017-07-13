(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DmDonViDeleteController',DmDonViDeleteController);

    DmDonViDeleteController.$inject = ['$uibModalInstance', 'entity', 'DmDonVi'];

    function DmDonViDeleteController($uibModalInstance, entity, DmDonVi) {
        var vm = this;

        vm.dmDonVi = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DmDonVi.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
