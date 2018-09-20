var ComponentController = function($scope,$http,$state,$location,DataService,EntityWS,$stateParams){


	$scope.selectedComponentDef = null;
	$scope.newComponentName = null;

	DataService.get(serverURL,'componentDefinition',true).then(function(componentsDef){
		$scope.componentsDef = componentsDef;
		console.log($scope.componentsDef)
	});


	$scope.createComponentdef = function(){
		var componentsDef = {
			'name':$scope.newComponentName
		}
		EntityWS.post(serverURL,'componentDefinition',componentsDef).then(function(result){
			$scope.componentsDef.push(result.data)
			$scope.selectComponentDef(result.data);
		})
	}


	$scope.selectComponentDef = function(componentDef){
		$scope.selectedComponentDef = componentDef;
	}


	$scope.addComponentParameter = function(){
		var parameterDef = {
			name:"",
			value:""
		}
		if($scope.selectedComponentDef.parametersDefs == undefined){
			$scope.selectedComponentDef.parametersDefs = new Array();
		}
		$scope.selectedComponentDef.parametersDefs.push(parameterDef);
	}

	$scope.saveComponentDef = function(){
		EntityWS.post(serverURL,'componentDefinition',$scope.selectedComponentDef).then(function(data){
			console.log(data)
		})
	}
}