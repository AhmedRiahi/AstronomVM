var OperationController = function($scope,$http,$state,$location,DataService,EntityWS,$stateParams,WebSocketService){
	console.log($stateParams)


	var self = this;

	$scope.project = DataService.find('project','name',$stateParams.projectName);
	if($scope.project == undefined){
		DataService.get(serverURL,'project',true).then(function(){
			$scope.project = DataService.find('project','name',$stateParams.projectName);
			self.init();
		});
	}
	
	$scope.selectedStep = null;
	$scope.selectedTransition = null;
	$scope.selectedSimulator = null;
	$scope.logs = "";
	


	self.init = function(){
		self.operationPlotter = new OperationPlotter();
		self.operationPlotter.init($scope);

		$scope.selectedOperation = null;

		$scope.project.metaFlows.forEach(function(operation){
			if(operation.name == $stateParams.operationName){
				$scope.selectedOperation = operation;
				self.operationPlotter.drawOperation($scope.selectedOperation);
			}
		});

		if($scope.project.metaFlows == undefined){
			$scope.project.metaFlows = new Array();
		}

		
		for(var i=0 ; i < $scope.project.metaFlows.length; i++){
			for(var j=0 ; j< $scope.project.metaFlows[i].steps.length ; j++){
				if($scope.project.metaFlows[i].steps[j].parametersValues == undefined){
					$scope.project.metaFlows[i].steps[j].parametersValues = new Array();
				}else{
					$scope.project.metaFlows[i].steps[j].parametersValues = JSON.parse($scope.project.metaFlows[i].steps[j].parametersValues).parameters;
				}
			}
		}
		

		DataService.get(serverURL,'simulator',true).then(function(simulators){
			$scope.simulators = simulators;
			$scope.selectedSimulator = simulators[0];
			$scope.simulatorSelectionChanged();
		});
	};


	$scope.simulatorSelectionChanged = function(){
		console.log($scope.selectedSimulator)
		DataService.get(serverURL,'componentMeta/'+$scope.selectedSimulator.id,true).then(function(componentsMeta){
			$scope.componentsMeta = componentsMeta;
			console.log($scope.componentsMeta)
		});
	}

	$scope.saveProject = function(){
		var tmpProject = JSON.parse(JSON.stringify($scope.project));
		for(var i=0 ; i < tmpProject.metaFlows.length; i++){

			for(var j=0 ; j< tmpProject.metaFlows[i].steps.length ; j++){
				tmpProject.metaFlows[i].steps[j].parametersValues = {}
				tmpProject.metaFlows[i].steps[j].parametersValues.parameters = new Array();
				for(var k=0 ; k < $scope.getComponentMeta(tmpProject.metaFlows[i].steps[j].componentName).parameterMetas.length ; k++){
					if($scope.project.metaFlows[i].steps[j].parametersValues == undefined){
						$scope.project.metaFlows[i].steps[j].parametersValues = new Array();
					}

					var parameterValue = $scope.project.metaFlows[i].steps[j].parametersValues[k] == undefined ? "" : $scope.project.metaFlows[i].steps[j].parametersValues[k].value;
					
					tmpProject.metaFlows[i].steps[j].parametersValues.parameters.push({
						name : $scope.getComponentMeta(tmpProject.metaFlows[i].steps[j].componentName).parameterMetas[k].name,
						value : parameterValue
					})
				}
				tmpProject.metaFlows[i].steps[j].parametersValues = JSON.stringify(tmpProject.metaFlows[i].steps[j].parametersValues);
			}


			for(var j=0 ; j< tmpProject.metaFlows[i].transitions.length ; j++){
				tmpProject.metaFlows[i].transitions[j].fromStep.parametersValues = "";
				tmpProject.metaFlows[i].transitions[j].toStep.parametersValues = "";
			}
		}

		


		EntityWS.post(serverURL,'project',tmpProject).then(function(data){
			console.log(data);
		})
	}


	$scope.addOperation = function(name){
		var operation = {};
		operation.name = name;
		$scope.project.metaFlows.push(operation);
	}

	$scope.addComponent = function(componentMeta){
		var step = {}
		step.componentName = componentMeta.name;
		step.graphicsProperties = {
			x : 300,
			y: 300
		}

		step.inputParameters = new Array();
		for(var i=0; i< componentMeta.parameterMetas.length; i++){
			step.inputParameters.push({
				'name': componentMeta.parameterMetas[i].name,
				'value':""
			})
		}
		
		
		if($scope.selectedOperation.steps == undefined){
			$scope.selectedOperation.steps = new Array();
		}
		step.name = step.componentName+"_"+$scope.selectedOperation.steps.length;
		$scope.selectedOperation.steps.push(step)
		self.operationPlotter.redraw()
	}

	$scope.selectOperation = function(operation){
		$scope.selectedOperation = operation;
		self.operationPlotter.drawOperation($scope.selectedOperation);
	}

	$scope.connectSteps = function(){
		var transition = {}
		transition.fromStep = self.operationPlotter.getSelectedNodes()[0];
		transition.toStep = self.operationPlotter.getSelectedNodes()[1];
		transition.sourceFlowName = "";
		transition.targetFlowName = "";
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
		$http.get(serverURL+'/astronomFlowExecutor'+'/execute/'+$scope.selectedSimulator.id+'/'+$scope.selectedOperation.id);
		WebSocketService.connect($scope.selectedSimulator.serviceUrl,$scope.selectedOperation.name,function(data){
			console.log('Received message from Websocket')
			console.log(data)
			$scope.logs += "\n"+data.body;
		})
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


	$scope.getComponentMeta = function(componentName){
		for(var i=0;i<$scope.componentsMeta.length;i++){
			if($scope.componentsMeta[i].name == componentName){
				return $scope.componentsMeta[i];
			}
		}
	}

}