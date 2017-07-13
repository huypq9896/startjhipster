(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('DmDonVi', DmDonVi);

    DmDonVi.$inject = ['$resource'];

    function DmDonVi ($resource) {
        var resourceUrl =  'api/dm-don-vis/:id';

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
