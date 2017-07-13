(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('DotXhSearch', DotXhSearch);

    DotXhSearch.$inject = ['$resource'];

    function DotXhSearch($resource) {
        var resourceUrl =  'api/_search/dot-xhs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
