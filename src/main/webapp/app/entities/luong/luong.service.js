(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('Luong', Luong);

    Luong.$inject = ['$resource'];

    function Luong ($resource) {
        var resourceUrl =  'api/luongs/:id';

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
