(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('DotXh', DotXh);

    DotXh.$inject = ['$resource', 'DateUtils'];

    function DotXh ($resource, DateUtils) {
        var resourceUrl =  'api/dot-xhs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.ngayCt = DateUtils.convertDateTimeFromServer(data.ngayCt);
                        data.lastCode = DateUtils.convertDateTimeFromServer(data.lastCode);
                        data.ngayKhoa = DateUtils.convertDateTimeFromServer(data.ngayKhoa);
                        data.ngay1 = DateUtils.convertDateTimeFromServer(data.ngay1);
                        data.ngay2 = DateUtils.convertDateTimeFromServer(data.ngay2);
                        data.ngay3 = DateUtils.convertDateTimeFromServer(data.ngay3);
                        data.ngayDuyet = DateUtils.convertDateTimeFromServer(data.ngayDuyet);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
