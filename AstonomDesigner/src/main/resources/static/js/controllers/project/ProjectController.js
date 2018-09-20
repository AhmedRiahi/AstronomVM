var ProjectController = function($scope,$http,$state,$location,DataService,EntityWS,$uibModal){


	$scope.project = {};
	$scope.selectedProject = null;
	$scope.projects = new Array();

	self.reloadProjects = function(){
		DataService.get(serverURL,'project',true).then(function(projects){
			$scope.projects = projects;
			console.log($scope.projects)
		})
	}

	self.reloadProjects()


	$scope.createProject = function(){
		EntityWS.post(serverURL,'project',$scope.project).then(function(result){
			$scope.projects.push(result.data)
		})
	}

	$scope.openProjectCreationDialog = function(){
		var modalInstance = $uibModal.open({
	      animation: true,
	      ariaLabelledBy: 'modal-title',
	      ariaDescribedBy: 'modal-body',
	      templateUrl: 'projectCreationModal.html',
	      controller: 'ProjectController',
	      controllerAs: '$ctrl'
	    });

	    modalInstance.result.then(function () {
	      self.reloadProjects();
	    }, function () {
	      console.log('modal-component dismissed at: ' + new Date());
	    });
	}

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};


	$scope.selectProject = function(project){
		$scope.selectedProject = project;
	}
}