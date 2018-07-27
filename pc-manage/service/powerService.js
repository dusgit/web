var tools = require('../utils/tools');
var settings = require('../settings');

var Power = {};

Power.findListPage = function(req,cb){
    var opts ={
        url:settings.server.url+'/power/list',
        qs:req.query
    }
    tools.http.get(req,opts,function(err,resp,body){
        cb(body);
    });
}
module.exports = Power;