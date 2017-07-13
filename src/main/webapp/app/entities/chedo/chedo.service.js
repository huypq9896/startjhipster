(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('Chedo', Chedo);

    Chedo.$inject = ['$resource'];

    function Chedo ($resource) {
        var resourceUrl =  'api/chedos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
