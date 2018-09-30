var WebSocketService = function(){

	var self = this;

	self.connect = function(url,subscriptionChanel,callback) {
		url = url.replace("Camirra-PC","localhost")
	    var socket = new SockJS(url+'monitoring');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        console.log('Connected: ' + frame);
	        stompClient.subscribe('/topic/orchestra/'+subscriptionChanel, function(data){callback(data);});
	        stompClient.subscribe('/topic/ping', function(){
	        	console.log("connected to ping chanel")
	        });
	    });
	};


	return self;
}