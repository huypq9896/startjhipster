(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('TuyenDtSearch', TuyenDtSearch);

    TuyenDtSearch.$inject = ['$resource'];

    function TuyenDtSearch($resource) {
        var resourceUrl =  'api/_search/tuyen-dts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
