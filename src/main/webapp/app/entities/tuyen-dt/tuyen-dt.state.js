(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tuyen-dt', {
            parent: 'entity',
            url: '/tuyen-dt?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.tuyenDt.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dts.html',
                    controller: 'TuyenDtController',
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
                    $translatePartialLoader.addPart('tuyenDt');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tuyen-dt-detail', {
            parent: 'tuyen-dt',
            url: '/tuyen-dt/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.tuyenDt.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dt-detail.html',
                    controller: 'TuyenDtDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tuyenDt');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TuyenDt', function($stateParams, TuyenDt) {
                    return TuyenDt.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tuyen-dt',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tuyen-dt-detail.edit', {
            parent: 'tuyen-dt-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dt-dialog.html',
                    controller: 'TuyenDtDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TuyenDt', function(TuyenDt) {
                            return TuyenDt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tuyen-dt.new', {
            parent: 'tuyen-dt',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dt-dialog.html',
                    controller: 'TuyenDtDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                ma: null,
                                ten: null,
                                soNgay: null,
                                active: null,
                                hscnId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tuyen-dt', null, { reload: 'tuyen-dt' });
                }, function() {
                    $state.go('tuyen-dt');
                });
            }]
        })
        .state('tuyen-dt.edit', {
            parent: 'tuyen-dt',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dt-dialog.html',
                    controller: 'TuyenDtDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TuyenDt', function(TuyenDt) {
                            return TuyenDt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tuyen-dt', null, { reload: 'tuyen-dt' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tuyen-dt.delete', {
            parent: 'tuyen-dt',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tuyen-dt/tuyen-dt-delete-dialog.html',
                    controller: 'TuyenDtDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TuyenDt', function(TuyenDt) {
                            return TuyenDt.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tuyen-dt', null, { reload: 'tuyen-dt' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
