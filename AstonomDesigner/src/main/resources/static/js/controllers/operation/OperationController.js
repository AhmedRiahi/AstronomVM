var OperationController = function($scope,$http,$state,$location,DataService,EntityWS,$stateParams){
	console.log($stateParams)


	var self = this;

	$scope.project = DataService.find('project','name',$stateParams.projectName);
	
	$scope.selectedStep = null;
	$scope.selectedTransition = null;
	self.operationPlotter = new OperationPlotter()
	self.operationPlotter.init($scope);

	$scope.selectedOperation = null

	$scope.project.operations.forEach(function(operation){
		if(operation.name == $stateParams.operationName){
			$scope.selectedOperation = operation;
			self.operationPlotter.drawOperation($scope.selectedOperation);
		}
	});

	if($scope.project.operations == undefined){
		$scope.project.operations = new Array();
	}

	DataService.get(serverURL,'componentMeta',true).then(function(componentsMeta){
		$scope.componentsMeta = componentsMeta;
		console.log($scope.componentsMeta)
	});

	$scope.saveProject = function(){
		EntityWS.post(serverURL,'project',$scope.project).then(function(data){
			console.log(data)
		})
	}

	$scope.addOperation = function(name){
		var operation = {};
		operation.name = name;
		$scope.project.operations.push(operation);
	}

	$scope.addComponent = function(componentMeta){
		var step = {}
		step.componentMeta = componentMeta;
		step.graphicsProperties = {
			x : 300,
			y: 300
		}

		step.inputParameters = new Array();
		for(var i=0; i< componentMeta.parameterMetas.length; i++){
			step.inputParameters.push({
				'name': componentMeta.parameterMetas[i],
				'value':""
			})
		}
		
		
		if($scope.selectedOperation.steps == undefined){
			$scope.selectedOperation.steps = new Array();
		}
		step.name = step.componentMeta.name+"_"+$scope.selectedOperation.steps.length;
		$scope.selectedOperation.steps.push(step)
		self.operationPlotter.redraw()
	}

	$scope.selectOperation = function(operation){
		$scope.selectedOperation = operation;
		self.operationPlotter.drawOperation($scope.selectedOperation);
	}

	$scope.connectSteps = function(){
		var transition = {}
		transition.fromStep = self.operationPlotter.getSelectedNodes()[0]
		transition.toStep = self.operationPlotter.getSelectedNodes()[1]
		if($scope.selectedOperation.transitions == undefined){
			$scope.selectedOperation.transitions = new Array();
		}
		$scope.selectedOperation.transitions.push(transition)
		self.operationPlotter.redraw()
	}


	$scope.exportOperation = function(){
		var dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify($scope.selectedOperation));
	    var downloadAnchorNode = document.createElement('a');
	    downloadAnchorNode.setAttribute("href",     dataStr);
	    downloadAnchorNode.setAttribute("download", $scope.selectedOperation.name + ".json");
	    document.body.appendChild(downloadAnchorNode); // required for firefox
	    downloadAnchorNode.click();
	    downloadAnchorNode.remove();
	}

	$scope.executeOperation = function(){
		$http.get(serverURL+'/engine'+'/executeOperation/'+$scope.selectedOperation.id);
	}

	$scope.selectStep = function(step){
		$scope.$apply(function(){$scope.selectedStep = step});
	}

	$scope.selectStepFromList = function(step){
		$scope.selectedStep = step;
	}

	$scope.transitionClicked = function(transition){
		$scope.$apply(function(){$scope.selectedTransition = transition});
	}

}