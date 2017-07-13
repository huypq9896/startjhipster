(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .controller('ChedoController', ChedoController);

    ChedoController.$inject = ['Chedo', 'ChedoSearch'];

    function ChedoController(Chedo, ChedoSearch) {

        var vm = this;

        vm.chedos = [];
        vm.clear = clear;
        vm.search = search;
        vm.loadAll = loadAll;

        loadAll();

        function loadAll() {
            Chedo.query(function(result) {
                vm.chedos = result;
                vm.searchQuery = null;
            });
        }

        function search() {
            if (!vm.searchQuery) {
                return vm.loadAll();
            }
            ChedoSearch.query({query: vm.searchQuery}, function(result) {
                vm.chedos = result;
                vm.currentSearch = vm.searchQuery;
            });
        }

        function clear() {
            vm.searchQuery = null;
            loadAll();
        }    }
})();
