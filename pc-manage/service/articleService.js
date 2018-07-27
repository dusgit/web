var tools = require('../utils/tools');
var settings = require('../settings');

var Article = {};

Article.findListPage = function(req,cb){
	var opts ={
			url:settings.server.url+'/article/list',
			qs:req.query
	}
	tools.http.get(req,opts,function(err,resp,body){
			cb(body);
	});
}

Article.addNetNews = function(req,cb){
	var opts ={
			url:settings.server.url+'/article/save',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

Article.updateNetNews = function(req,cb){
	var opts ={
			url:settings.server.url+'/article/update',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

Article.deleteNetNews = function(req,cb){
	var opts ={
			url:settings.server.url+'/article/delete',
			form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
			cb(body);
	});
}

module.exports = Article;