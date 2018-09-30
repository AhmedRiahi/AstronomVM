var AstronomDesigner = angular.module('AstronomDesigner',['ngResource','ui.router','ui.bootstrap','ngToast']);

// Dashboard views configuration
AstronomDesigner.config(function($stateProvider,$urlRouterProvider){

	$urlRouterProvider.otherwise('project');

	$stateProvider
	.state('project',{
		url 		: '/project',
		templateUrl	: 'views/project/index.html',
		controller 	: ProjectController
	}).state('operation',{
		url 		: '/operation/:projectName/:operationName',
		templateUrl	: 'views/operation/index.html',
		controller 	: OperationController
	}).state('components',{
		url 		: '/components',
		templateUrl	: 'views/component/index.html',
		controller 	: ComponentController
	}).state('entities',{
		url 		: '/entities',
		templateUrl	: 'views/functionalEntities/index.html',
		controller 	: EntitiesController
	}).state('configuration',{
		url 		: '/configuration',
		templateUrl	: 'views/configuration/index.html',
		controller 	: ConfigurationController
	});
});


// ************** Controllers Management **************

AstronomDesigner.factory('EntityWS',EntityWS);
AstronomDesigner.factory('DataService',DataService);
AstronomDesigner.factory('WebSocketService',WebSocketService)

AstronomDesigner.controller('NavbarController',NavbarController);
AstronomDesigner.controller('ProjectController',ProjectController);



// ************** Services Management **************
var serverHost 	= window.location.hostname;
var serverURL 	= 'http://'+serverHost+':9000/';
var Services 	= angular.module('Services',['ngResource']);