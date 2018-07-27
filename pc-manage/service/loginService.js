var request  = require('request');
var settings = require('../settings');
var tools    = require('../utils/tools');
var Login    = {};


Login.verifyLogin = function(req,res,cb){
	var opts = req.body;
	if(!opts['userName']){
		cb({status:'false',message:'用户名称不能为空'});
		return;
		
	}
	if(!opts['userPass']){
		cb({status:'false',message:'用户密码不能为空'});
		return;
	}
	var form = {
		userName : req.body['userName'],
		userPass : req.body['userPass'],
		ip       : tools.getClientIp(req)
	}
	console.log('kais'+req.body['userName']);
	tools.mysql.query('select * from web_manage_user where user_name = ? ',[req.body['userName']],function(err,results){
		if(err){
			console.log(err);
			cb({status:'false',message:'失败'});
			return;
		}
		// 判断密码是否正确
		var userInfo = results[0];
		console.log(results[0]['user_name']);
		var token = tools.stringUtil.uuid();
		var cookie_token = tools.enCode(token,tools.getClientIp(req));
		res.cookie('token', cookie_token);
		console.log('设置redis值：'+token);
		tools.redis.set(token,JSON.stringify(results[0]),60*30);//保存用户相关信息
		cb({status:'success',message:'登录成功'});
	});
}

module.exports = Login;