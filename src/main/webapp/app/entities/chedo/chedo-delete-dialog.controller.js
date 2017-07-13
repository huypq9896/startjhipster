(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('ChedoDeleteController',ChedoDeleteController);

    ChedoDeleteController.$inject = ['$uibModalInstance', 'entity', 'Chedo'];

    function ChedoDeleteController($uibModalInstance, entity, Chedo) {
        var vm = this;

        vm.chedo = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Chedo.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
