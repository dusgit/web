var settings = require('../settings');
var redis = require("redis");

var RedisTool = {}

RedisTool.init = function(){
	if(!RedisTool.client){
		var opts = settings.redis;
		console.log('redis connect');
		RedisTool.client = redis.createClient(opts);
		RedisTool.client.on("error", function (err) {
			console.log("Error " + err);
		});
	}
}

RedisTool.set = function(key,value,timeout){
	this.init();
	this.client.set(key,value,'EX',timeout||60*30);//default 30 minutes
}

RedisTool.get = function(key,cb){
	this.init();
	this.client.get(key,cb);
}

module.exports = RedisTool;


