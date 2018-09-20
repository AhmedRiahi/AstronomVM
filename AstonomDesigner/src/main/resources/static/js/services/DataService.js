var DataService = function($q,EntityWS){
	
	var self = this;

	self.data = [];


	self.put = function(name,value,isArray = false){
		if(!isArray){
			self.data[name] = value;
		}else{
			var array = self.data[name]
			if(array == undefined) array = new Array()
			array.push(value)
			self.data[name] = array;
		}
	}

	self.putArray = function(name,array){
		self.data[name] = array;
	}

	self.getLocal = function(name){
		self.data[name];
	}

	self.get = function(url,name,isArray = false){
		result = self.data[name];
		var deferred = $q.defer();

		if(result != undefined){
			deferred.resolve(result)
		}else{
			if(isArray){
				EntityWS.getAll(url,name).then(function(response){
					self.putArray(name,response.data,true);
					deferred.resolve(response.data);
				})
			}else{
				EntityWS.get(url,name).then(function(response){
					self.put(name,response.data);
					deferred.resolve(response.data);
				});
			}
		}
		return deferred.promise;
	}

	self.find = function(name,filter,value){
		var entities = self.data[name]
		if(entities != undefined){
			for(var i=0; i < Object.keys(entities).length;i++){
				var index = Object.keys(entities)[i]
				if(entities[index][filter] == value){
					return entities[index];
				}
			}
		}else{
			throw "Data not found "+name+" "+filter+" "+value
		}
		
	}

	return self;

}