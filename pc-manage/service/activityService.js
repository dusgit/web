var tools = require('../utils/tools');
var settings = require('../settings');

var Activity = {};

Activity.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/activity/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

Activity.addActivity = function(req,cb){
    var opts = {
        url:settings.server.url+'/activity/save',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Activity.findActivityImagesList = function(req,cb){
    var opts ={
        url:settings.server.url+'/attachImages/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

Activity.updateActivity = function(req,cb){
    var opts = {
        url:settings.server.url+'/activity/update',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Activity.deleteActivity = function(req,cb){
    var opts = {
        url:settings.server.url+'/activity/delete',
        form:req.body
    }
    tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
module.exports = Activity;