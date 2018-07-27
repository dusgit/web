var express = require('express');
var router  = express.Router();
var request = require('request');
var tools   = require('../utils/tools');
var loginService = require('../service/loginService');

router.get('/', function(req, res, next) {
  res.render('login',{});
});

router.get('/enCode', function(req, res, next) {
    res.end(tools.enCode(req.query.token,tools.getClientIp(req)));
});

router.get('/deCode', function(req, res, next) {
    res.end(tools.deCode(req.query.token,tools.getClientIp(req)));
});

router.get('/login.html', function(req, res, next) {
  res.render('login',{});
});

router.get('/loginOut.html', function(req, res, next) {
  res.cookie('token', '');
  res.render('login',{});
});

router.post('/verifyLogin', function(req, res, next) {
	loginService.verifyLogin(req,res,function(result){
		if(typeof result == 'object'){
			console.log(result);
			res.json(result);
		}else{
			res.end(res);
		}
	});
});


module.exports = router;
