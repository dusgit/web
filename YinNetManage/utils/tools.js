var underscore = require('underscore');
var secure = require('./secure');
var redis = require('./redis');
var http = require('./httpUtil');



var Tools = {
	_           : underscore,
	getClientIp : secure.getClientIp,
	enCode      : secure.enCode,
	deCode      : secure.deCode,
	redis       : redis,
	http        : http
};

module.exports = Tools;

