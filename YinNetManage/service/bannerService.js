var tools = require('../utils/tools');
var settings = require('../settings');

var Banner = {};

Banner.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/banner/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        console.log(body+"好");
        cb(body);
    });
}

Banner.uploadImg = function(req,cb){
	var opts = {
		url:settings.server.url+'/qiniu/getUpToken',
		qs:req.query
	}
	tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

Banner.addBanner = function(req,cb){
    var opts = {
		url:settings.server.url+'/banner/save',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Banner.updateBanner = function(req,cb){
    var opts = {
		url:settings.server.url+'/banner/update',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Banner.deleteBanner = function(req,cb){
    var opts = {
		url:settings.server.url+'/banner/delete',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
module.exports = Banner;