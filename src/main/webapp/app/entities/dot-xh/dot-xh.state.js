(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dot-xh', {
            parent: 'entity',
            url: '/dot-xh?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.dotXh.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dot-xh/dot-xhs.html',
                    controller: 'DotXhController',
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
                    $translatePartialLoader.addPart('dotXh');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dot-xh-detail', {
            parent: 'dot-xh',
            url: '/dot-xh/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.dotXh.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dot-xh/dot-xh-detail.html',
                    controller: 'DotXhDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('dotXh');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DotXh', function($stateParams, DotXh) {
                    return DotXh.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dot-xh',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dot-xh-detail.edit', {
            parent: 'dot-xh-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dot-xh/dot-xh-dialog.html',
                    controller: 'DotXhDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DotXh', function(DotXh) {
                            return DotXh.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dot-xh.new', {
            parent: 'dot-xh',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dot-xh/dot-xh-dialog.html',
                    controller: 'DotXhDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                maDv: null,
                                maQt: null,
                                loaiDt: null,
                                maNt: null,
                                tyGia: null,
                                ngayCt: null,
                                soPhieu: null,
                                soLd: null,
                                tql: null,
                                userCode: null,
                                lastCode: null,
                                sldNu: null,
                                maQl: null,
                                maTinh: null,
                                listNgaynghi: null,
                                khoaSo: null,
                                ngayKhoa: null,
                                thongTin1: null,
                                thongTin2: null,
                                thongTin3: null,
                                ngay1: null,
                                ngay2: null,
                                ngay3: null,
                                ghiChu: null,
                                ngayDuyet: null,
                                nguoiDuyet: null,
                                keySl: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dot-xh', null, { reload: 'dot-xh' });
                }, function() {
                    $state.go('dot-xh');
                });
            }]
        })
        .state('dot-xh.edit', {
            parent: 'dot-xh',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dot-xh/dot-xh-dialog.html',
                    controller: 'DotXhDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DotXh', function(DotXh) {
                            return DotXh.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dot-xh', null, { reload: 'dot-xh' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dot-xh.delete', {
            parent: 'dot-xh',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dot-xh/dot-xh-delete-dialog.html',
                    controller: 'DotXhDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DotXh', function(DotXh) {
                            return DotXh.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dot-xh', null, { reload: 'dot-xh' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
