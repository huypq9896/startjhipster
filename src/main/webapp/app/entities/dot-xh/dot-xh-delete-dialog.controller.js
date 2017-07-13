(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('DotXhDeleteController',DotXhDeleteController);

    DotXhDeleteController.$inject = ['$uibModalInstance', 'entity', 'DotXh'];

    function DotXhDeleteController($uibModalInstance, entity, DotXh) {
        var vm = this;

        vm.dotXh = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DotXh.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
