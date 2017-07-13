(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('hscn', {
            parent: 'entity',
            url: '/hscn?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.hscn.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/hscn/hscns.html',
                    controller: 'HscnController',
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
                    $translatePartialLoader.addPart('hscn');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('hscn-detail', {
            parent: 'hscn',
            url: '/hscn/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.hscn.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/hscn/hscn-detail.html',
                    controller: 'HscnDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('hscn');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Hscn', function($stateParams, Hscn) {
                    return Hscn.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'hscn',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('hscn-detail.edit', {
            parent: 'hscn-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hscn/hscn-dialog.html',
                    controller: 'HscnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Hscn', function(Hscn) {
                            return Hscn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('hscn.new', {
            parent: 'hscn',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hscn/hscn-dialog.html',
                    controller: 'HscnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                soBhxh: null,
                                hoTen: null,
                                gioiTinh: null,
                                ngaySinh: null,
                                soCmnd: null,
                                diaChiLh: null,
                                ngayCap: null,
                                noiCap: null,
                                dan_toc: null,
                                quoc_tich: null,
                                noi_khai: null,
                                diachiHK: null,
                                dien_thoai: null,
                                email: null,
                                chuc_vu: null,
                                maPB: null,
                                phong_ban: null,
                                maCV: null,
                                maNV: null,
                                noicapBHXH: null,
                                maDT: null,
                                maBV: null,
                                ghi_chu: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('hscn', null, { reload: 'hscn' });
                }, function() {
                    $state.go('hscn');
                });
            }]
        })
        .state('hscn.edit', {
            parent: 'hscn',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hscn/hscn-dialog.html',
                    controller: 'HscnDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Hscn', function(Hscn) {
                            return Hscn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('hscn', null, { reload: 'hscn' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('hscn.delete', {
            parent: 'hscn',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/hscn/hscn-delete-dialog.html',
                    controller: 'HscnDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Hscn', function(Hscn) {
                            return Hscn.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('hscn', null, { reload: 'hscn' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
