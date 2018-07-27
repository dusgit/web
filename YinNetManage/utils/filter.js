var tools = require('./tools');
var Filter = {}


Filter.start = function(req,res,next){
	//res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	//验证token
	var token_cookie = req.cookies['token'];
	var token = tools.deCode(token_cookie,tools.getClientIp(req));
	if(token){
		if(req.url.indexOf('.html') > 0){
			tools.redis.get(token,function(err,result){
				if(result){
					result = JSON.parse(result);
					console.log(result);
					console.log(typeof result['userInfo']);
					console.log(typeof result['powerMap']);
					var results = {
						title : '管理后台',
						menuListStr : JSON.stringify(result['menuList']||{}),
						userInfo : result['userInfo'],
						powerMap : JSON.stringify(result['powerMap']||{})
					}
					res['results'] = results;
					next();
				}else{
					res.render('login',{});
				}
			});
		}else{
			next();
		}
	}else{
		var urlArray = [
			'/',
			'/login.html',
			'/loginOut.html',
			'/verifyLogin'
		]
		if(urlArray.indexOf(req.url) >= 0){
			next();
		}else{
			res.end('client:invalid request');
		}
	}
}


module.exports = Filter;