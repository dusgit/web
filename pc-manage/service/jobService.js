var tools = require('../utils/tools');
var settings = require('../settings');

var NetJob = {};

NetJob.findListPage = function(req,cb){
	var opts ={
			url:settings.server.url+'/job/list',
			qs:req.query
	}
	tools.http.get(req,opts,function(err,resp,body){
			cb(body);``
	});
}

NetJob.addNetJob = function(req,cb){
	var opts ={
			url:settings.server.url+'/job/save',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

NetJob.updateNetJob = function(req,cb){
	var opts ={
			url:settings.server.url+'/job/update',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

NetJob.deleteNetJob = function(req,cb){
	var opts ={
			url:settings.server.url+'/job/delete',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

module.exports = NetJob;