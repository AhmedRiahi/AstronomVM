var EntitiesController = function($scope,$http,$state,$location,DataService,EntityWS,$stateParams){

	DataService.get(serverURL,'functionalEntity',true).then(function(functionalEntities){
		$scope.functionalEntities = functionalEntities;
		console.log($scope.functionalEntities)
	});


	$scope.currentEntity = {};


	$scope.createEntity = function(){
		EntityWS.post(serverURL,'functionalEntity',$scope.currentEntity).then(function(data){
			console.log(data)
		})
	}

	$scope.selectEntity = function(entity){
		$scope.currentEntity = entity
	}


	$scope.addField = function(){
		var field = {name:'',type:''}
		if($scope.currentEntity.fields == undefined){
			$scope.currentEntity.fields = new Array()
		}
		$scope.currentEntity.fields.push(field)
	}


	$scope.deleteEntity = function(entity){
		EntityWS.delete(serverURL,'functionalEntity',entity);
	}
}