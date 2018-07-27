var tools = require('../utils/tools');
var settings = require('../settings');

var Image = {};

Image.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/image/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}

// Image.uploadImg = function(req,cb){
// 	var opts = {
// 		url:settings.server.url+'/qiniu/getUpToken',
// 		qs:req.query
// 	}
// 	tools.http.get(req,opts,function(err,resp,body){
//         cb(body);
//     });
// }

Image.addImage = function(req,cb){
    var opts = {
		url:settings.server.url+'/image/save',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Image.updateImage = function(req,cb){
    var opts = {
		url:settings.server.url+'/image/update',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}

Image.deleteImage = function(req,cb){
    var opts = {
		url:settings.server.url+'/image/delete',
		form:req.body
	}
	tools.http.post(req,opts,function(err,resp,body){
        cb(body);
    });
}
module.exports = Image;