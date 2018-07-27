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
	if(settings.dynamic.verify){
		if(!opts['dynamicCode']){
			cb({status:'false',message:'动态验证码不能为空'});
			return;
		}
		var form = {
			ak      : settings.dynamic.login_token,
			account : opts['userName'],
			code    : opts['dynamicCode']
		}
		request.post({url:settings.dynamic.check_url, form: form}, function(err,httpResponse,body){
			if(err){
				cb({status:'false',message:err});
				return;
			}else{
				body = JSON.parse(body);
				console.log(body['object'] == false);
				console.log(body);
				if(body['object'] == false){
					cb({status:'false',message:'动态验证码验证失败'});
					return;
				}else{
					_verifyUserLogin(req,res,cb);
				}
			}
		})
	}else{
		_verifyUserLogin(req,res,cb);
	}
}

function _verifyUserLogin(req,res,cb){
	var form = {
		userName : req.body['userName'],
		userPass : req.body['userPass'],
		ip       : tools.getClientIp(req)
	}
	var url = settings.server.url + '/login/verifyLogin';
	request.post({url:url, form: form}, function(err,httpResponse,body){
		if(err){
			cb({status:'false',message:err});
			return;
		}else{
			if(typeof body == 'string'){
				body = JSON.parse(body);
			}
			if(body['status'] == 'false'){
				cb({status:'false',message:body['message']});
				return;
			}else if(body['status'] == 'success'){
				var token = body['results']['token'];
				if(token){
					var cookie_token = tools.enCode(token,tools.getClientIp(req));
					res.cookie('token', cookie_token);
					console.log('设置redis值：'+token);
					tools.redis.set(token,JSON.stringify(body['results']),60*30);//保存用户相关信息
					cb({status:'success',message:'登录成功'});
				}else{
					cb({status:'false',message:body['message']});
				}
			}else{
				cb({status:'false',message:'服务忙，稍后再试'});
			}
		}
	})
}


module.exports = Login;