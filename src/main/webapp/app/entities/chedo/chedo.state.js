(function() {
    'use strict';

    angular
        .module('appsmysqlApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('chedo', {
            parent: 'entity',
            url: '/chedo',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.chedo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/chedo/chedos.html',
                    controller: 'ChedoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('chedo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('chedo-detail', {
            parent: 'chedo',
            url: '/chedo/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'appsmysqlApp.chedo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/chedo/chedo-detail.html',
                    controller: 'ChedoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('chedo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Chedo', function($stateParams, Chedo) {
                    return Chedo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'chedo',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('chedo-detail.edit', {
            parent: 'chedo-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/chedo/chedo-dialog.html',
                    controller: 'ChedoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Chedo', function(Chedo) {
                            return Chedo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('chedo.new', {
            parent: 'chedo',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/chedo/chedo-dialog.html',
                    controller: 'ChedoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                maCD: null,
                                tenCD: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('chedo', null, { reload: 'chedo' });
                }, function() {
                    $state.go('chedo');
                });
            }]
        })
        .state('chedo.edit', {
            parent: 'chedo',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/chedo/chedo-dialog.html',
                    controller: 'ChedoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Chedo', function(Chedo) {
                            return Chedo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('chedo', null, { reload: 'chedo' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('chedo.delete', {
            parent: 'chedo',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/chedo/chedo-delete-dialog.html',
                    controller: 'ChedoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Chedo', function(Chedo) {
                            return Chedo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('chedo', null, { reload: 'chedo' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
