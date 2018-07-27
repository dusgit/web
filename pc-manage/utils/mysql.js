var settings = require('../settings');
var mysql = require('mysql');

var MYSQL = {};

MYSQL.getPool = function(){
	if(!MYSQL.pool){
		MYSQL.pool = mysql.createPool(settings.mysql);
	}
	return MYSQL.pool;
}

MYSQL.sql = function(sql,opts,cb){
	MYSQL.getPool().getConnection(function(err, connection) {
	    if (err){
			cb(err);
			return;
		}
	    connection.query(sql,opts, function (error, results, fields) {
			connection.release();
			cb(error,results);
	    });
	});
}

MYSQL.query = function(sql,opts,cb){
	MYSQL.sql(sql,opts,cb);
}

MYSQL.insert = function(sql,opts,cb){
	MYSQL.sql(sql,opts,cb);
}

MYSQL.update = function(sql,opts,cb){
	MYSQL.sql(sql,opts,cb);
}

module.exports = MYSQL;