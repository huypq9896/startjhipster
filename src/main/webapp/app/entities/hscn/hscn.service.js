(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('Hscn', Hscn);

    Hscn.$inject = ['$resource'];

    function Hscn ($resource) {
        var resourceUrl =  'api/hscns/:id';

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
