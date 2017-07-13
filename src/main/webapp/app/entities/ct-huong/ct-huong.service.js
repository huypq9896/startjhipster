(function() {
    'use strict';
    angular
        .module('appsmysqlApp')
        .factory('CtHuong', CtHuong);

    CtHuong.$inject = ['$resource', 'DateUtils'];

    function CtHuong ($resource, DateUtils) {
        var resourceUrl =  'api/ct-huongs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.ngayDuyet = DateUtils.convertDateTimeFromServer(data.ngayDuyet);
                        data.tuNgay = DateUtils.convertDateTimeFromServer(data.tuNgay);
                        data.denNgay = DateUtils.convertDateTimeFromServer(data.denNgay);
                        data.tuNgayH = DateUtils.convertDateTimeFromServer(data.tuNgayH);
                        data.denNgayH = DateUtils.convertDateTimeFromServer(data.denNgayH);
                        data.ngay1 = DateUtils.convertDateTimeFromServer(data.ngay1);
                        data.ngayNuoi = DateUtils.convertDateTimeFromServer(data.ngayNuoi);
                        data.tuNgayBs = DateUtils.convertDateTimeFromServer(data.tuNgayBs);
                        data.denNgayBs = DateUtils.convertDateTimeFromServer(data.denNgayBs);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
