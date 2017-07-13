(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('TuyenDt', TuyenDt);

    TuyenDt.$inject = ['$resource'];

    function TuyenDt ($resource) {
        var resourceUrl =  'api/tuyen-dts/:id';

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
