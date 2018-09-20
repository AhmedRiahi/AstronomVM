var EntityWS = function($http){
	var self = this;

	self.getAll = function(url,entityName){
		return $http.get(url+entityName+'/getAll');
	}

	self.get = function(url,entityName){
		return $http.get(url+entityName+'/get');
	}

	self.post = function(url,entityName,entity){
		return $http.post(url+entityName+'/create',entity);
	}

	self.delete = function(url,entityName,entity){
		return $http.delete(url+entityName+'/delete',entity);
	}

	return self;
}