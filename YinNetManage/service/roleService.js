var tools = require('../utils/tools');
var settings = require('../settings');

var Role = {};

Role.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/role/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}
Role.addRole = function(req,cb){
    var opts ={
        url:settings.server.url+'/role/save',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
Role.updateRole = function(req,cb){
    var opts ={
        url:settings.server.url+'/role/update',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
Role.deleteRole = function(req,cb){
    var opts ={
        url:settings.server.url+'/role/delete',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
Role.findRolePowerList = function(req,cb){
    var opts ={
        url:settings.server.url+'/role/findRolePowerList',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}
module.exports = Role;