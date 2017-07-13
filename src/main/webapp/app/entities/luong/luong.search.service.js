(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('LuongSearch', LuongSearch);

    LuongSearch.$inject = ['$resource'];

    function LuongSearch($resource) {
        var resourceUrl =  'api/_search/luongs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
