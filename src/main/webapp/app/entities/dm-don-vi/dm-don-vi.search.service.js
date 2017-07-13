(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .factory('DmDonViSearch', DmDonViSearch);

    DmDonViSearch.$inject = ['$resource'];

    function DmDonViSearch($resource) {
        var resourceUrl =  'api/_search/dm-don-vis/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
