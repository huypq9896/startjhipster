(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('CtHuongSearch', CtHuongSearch);

    CtHuongSearch.$inject = ['$resource'];

    function CtHuongSearch($resource) {
        var resourceUrl =  'api/_search/ct-huongs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
