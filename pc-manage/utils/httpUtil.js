var request = require('request');
var secure = require('./secure');
var _ = require('underscore');
var Http = {};

Http.get = function(req,opts,cb){
	var cookies_token = req.cookies['token'];
	var token = '';
	try{
		token = secure.deCode(cookies_token,secure.getClientIp(req));
	}catch(e){
		console.log(e);
	}
	if(!token){
		cb({status:'false',message:'no token'});
		return;
	}
	request.get(_.extend(opts,{headers:{token:token}}),cb);
}

Http.post = function(req,opts,cb){
	var cookies_token = req.cookies['token'];
	var token = '';
	try{
		token = secure.deCode(cookies_token,secure.getClientIp(req));
	}catch(e){
		console.log(e);
	}
	if(!token){
		cb({status:'false',message:'no token'});
		return;
	}
	request.post(_.extend(opts,{headers:{token:token}}),cb);
}

module.exports = Http;