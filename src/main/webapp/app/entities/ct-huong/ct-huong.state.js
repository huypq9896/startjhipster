(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ct-huong', {
            parent: 'entity',
            url: '/ct-huong?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.ctHuong.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ct-huong/ct-huongs.html',
                    controller: 'CtHuongController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ctHuong');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ct-huong-detail', {
            parent: 'ct-huong',
            url: '/ct-huong/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.ctHuong.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ct-huong/ct-huong-detail.html',
                    controller: 'CtHuongDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ctHuong');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CtHuong', function($stateParams, CtHuong) {
                    return CtHuong.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ct-huong',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ct-huong-detail.edit', {
            parent: 'ct-huong-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ct-huong/ct-huong-dialog.html',
                    controller: 'CtHuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CtHuong', function(CtHuong) {
                            return CtHuong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ct-huong.new', {
            parent: 'ct-huong',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ct-huong/ct-huong-dialog.html',
                    controller: 'CtHuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                keysl: null,
                                keyYp: null,
                                maDv: null,
                                soBhxh: null,
                                maNv: null,
                                hoTen: null,
                                mucLuong: null,
                                ngayDuyet: null,
                                nguoiDuyet: null,
                                loaiDt: null,
                                maNt: null,
                                mlTt: null,
                                maCd: null,
                                maNh: null,
                                tuNgay: null,
                                denNgay: null,
                                tuNgayH: null,
                                denNgayH: null,
                                soNgay: null,
                                soNgayH: null,
                                soNgayLk: null,
                                soTien: null,
                                soTienH: null,
                                soNamBh: null,
                                soThangBh: null,
                                soNgayLkdv: null,
                                ghiChu: null,
                                dk1: null,
                                dk2: null,
                                dk5: null,
                                dk6: null,
                                tuoiCon: null,
                                sttCon: null,
                                gioiTinh: null,
                                maQt: null,
                                dkBenhDai: null,
                                dkPhauthuat: null,
                                dkNghionha: null,
                                dkSuygiamld: null,
                                dkXacsyt: null,
                                dk3Ca: null,
                                ngay1: null,
                                tenCon: null,
                                stt: null,
                                maQl: null,
                                maTinh: null,
                                loaidc: null,
                                lydodc: null,
                                ghichudc: null,
                                loaiBenh: null,
                                tuyenBv: null,
                                lbqhs: null,
                                lbqml: null,
                                troCap: null,
                                troCapBh: null,
                                dk3: null,
                                dk4: null,
                                tyleh: null,
                                ngayNuoi: null,
                                soNgayCd: null,
                                ngayNghiList: null,
                                vssid: null,
                                tuNgayBs: null,
                                denNgayBs: null,
                                chk1: null,
                                chk2: null,
                                chk3: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ct-huong', null, { reload: 'ct-huong' });
                }, function() {
                    $state.go('ct-huong');
                });
            }]
        })
        .state('ct-huong.edit', {
            parent: 'ct-huong',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ct-huong/ct-huong-dialog.html',
                    controller: 'CtHuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CtHuong', function(CtHuong) {
                            return CtHuong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ct-huong', null, { reload: 'ct-huong' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ct-huong.delete', {
            parent: 'ct-huong',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ct-huong/ct-huong-delete-dialog.html',
                    controller: 'CtHuongDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CtHuong', function(CtHuong) {
                            return CtHuong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ct-huong', null, { reload: 'ct-huong' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
