(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('dm-don-vi', {
            parent: 'entity',
            url: '/dm-don-vi?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.dmDonVi.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vis.html',
                    controller: 'DmDonViController',
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
                    $translatePartialLoader.addPart('dmDonVi');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('dm-don-vi-detail', {
            parent: 'dm-don-vi',
            url: '/dm-don-vi/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.dmDonVi.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vi-detail.html',
                    controller: 'DmDonViDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('dmDonVi');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DmDonVi', function($stateParams, DmDonVi) {
                    return DmDonVi.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'dm-don-vi',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('dm-don-vi-detail.edit', {
            parent: 'dm-don-vi-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vi-dialog.html',
                    controller: 'DmDonViDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DmDonVi', function(DmDonVi) {
                            return DmDonVi.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dm-don-vi.new', {
            parent: 'dm-don-vi',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vi-dialog.html',
                    controller: 'DmDonViDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                ma: null,
                                name: null,
                                diaChi: null,
                                ghiChu: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('dm-don-vi', null, { reload: 'dm-don-vi' });
                }, function() {
                    $state.go('dm-don-vi');
                });
            }]
        })
        .state('dm-don-vi.edit', {
            parent: 'dm-don-vi',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vi-dialog.html',
                    controller: 'DmDonViDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DmDonVi', function(DmDonVi) {
                            return DmDonVi.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dm-don-vi', null, { reload: 'dm-don-vi' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('dm-don-vi.delete', {
            parent: 'dm-don-vi',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/dm-don-vi/dm-don-vi-delete-dialog.html',
                    controller: 'DmDonViDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DmDonVi', function(DmDonVi) {
                            return DmDonVi.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('dm-don-vi', null, { reload: 'dm-don-vi' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
