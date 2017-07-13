(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('luong', {
            parent: 'entity',
            url: '/luong?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.luong.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/luong/luongs.html',
                    controller: 'LuongController',
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
                    $translatePartialLoader.addPart('luong');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('luong-detail', {
            parent: 'luong',
            url: '/luong/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.luong.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/luong/luong-detail.html',
                    controller: 'LuongDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('luong');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Luong', function($stateParams, Luong) {
                    return Luong.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'luong',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('luong-detail.edit', {
            parent: 'luong-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/luong/luong-dialog.html',
                    controller: 'LuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Luong', function(Luong) {
                            return Luong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('luong.new', {
            parent: 'luong',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/luong/luong-dialog.html',
                    controller: 'LuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                mucLuong: null,
                                thang: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('luong', null, { reload: 'luong' });
                }, function() {
                    $state.go('luong');
                });
            }]
        })
        .state('luong.edit', {
            parent: 'luong',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/luong/luong-dialog.html',
                    controller: 'LuongDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Luong', function(Luong) {
                            return Luong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('luong', null, { reload: 'luong' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('luong.delete', {
            parent: 'luong',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/luong/luong-delete-dialog.html',
                    controller: 'LuongDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Luong', function(Luong) {
                            return Luong.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('luong', null, { reload: 'luong' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
