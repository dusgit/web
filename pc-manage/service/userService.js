var tools = require('../utils/tools');
var settings = require('../settings');

var User = {};

User.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/user/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        console.log(body+"好");
        cb(body);
    });
}

User.addUser = function(req,cb){
    var opts ={
        url:settings.server.url+'/user/save',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

User.updateUser = function(req,cb){
    var opts ={
        url:settings.server.url+'/user/update',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

User.deleteUser = function(req,cb){
    var opts ={
        url:settings.server.url+'/user/delete',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

User.findRoleList = function(req,cb){
	var opts ={
        url:settings.server.url+'/role/list'
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

User.findCurrentRoleList = function(req,cb){
	var opts ={
        url:settings.server.url+'/user/findLoginUserRoleList'
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}
User.findUserRoleList = function(req,cb){
	var opts ={
        url:settings.server.url+'/user/findUserRoleList',
		qs : {
			id : req.query.id
		}
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

module.exports = User;