var ConfigurationController = function($scope,$http,$state,$location,DataService,EntityWS,$stateParams){


	$scope.currentDBConnection = null;

	DataService.get(serverURL,'databaseConnection',true).then(function(dbConnections){
		$scope.dbConnections = dbConnections;
		console.log($scope.dbConnections)
	});

	$scope.createDBConnection = function(){
		EntityWS.post(serverURL,'databaseConnection',$scope.currentDBConnection).then(function(data){
			console.log(data)
		})
	}

	$scope.selectDBConnection = function(dbConnection){
		$scope.currentDBConnection = dbConnection;
	}
	
}