(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('HscnDeleteController',HscnDeleteController);

    HscnDeleteController.$inject = ['$uibModalInstance', 'entity', 'Hscn'];

    function HscnDeleteController($uibModalInstance, entity, Hscn) {
        var vm = this;

        vm.hscn = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Hscn.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
