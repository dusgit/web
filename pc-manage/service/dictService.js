var tools = require('../utils/tools');
var settings = require('../settings');

var SysDict = {};

SysDict.findListPage = function(req,cb){
	var opts ={
			url:settings.server.url+'/dict/list',
			qs:req.query
	}
	tools.http.get(req,opts,function(err,resp,body){
			cb(body);``
	});
}

SysDict.addSysDict = function(req,cb){
	var opts ={
			url:settings.server.url+'/dict/save',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

SysDict.updateSysDict = function(req,cb){
	var opts ={
			url:settings.server.url+'/dict/update',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

SysDict.deleteSysDict = function(req,cb){
	var opts ={
			url:settings.server.url+'/dict/delete',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

module.exports = SysDict;