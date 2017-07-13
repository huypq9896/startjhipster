(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('ChedoSearch', ChedoSearch);

    ChedoSearch.$inject = ['$resource'];

    function ChedoSearch($resource) {
        var resourceUrl =  'api/_search/chedos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
