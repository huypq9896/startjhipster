(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('HscnSearch', HscnSearch);

    HscnSearch.$inject = ['$resource'];

    function HscnSearch($resource) {
        var resourceUrl =  'api/_search/hscns/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
