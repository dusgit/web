var underscore = require('underscore');
var secure = require('./secure');
var redis = require('./redis');
var http = require('./httpUtil');
var mysql = require('./mysql');
var stringUtil = require('./stringUtil');
var table = require('./table');


var Tools = {
	_           : underscore,
	getClientIp : secure.getClientIp,
	enCode      : secure.enCode,
	deCode      : secure.deCode,
	redis       : redis,
	http        : http,
	mysql       : mysql,
	stringUtil  : stringUtil,
	table       : table
};

module.exports = Tools;

