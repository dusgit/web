var Settings = {
	dynamic : {
		verify:false,
		login_token:'29D7A0646388453B863C47DE4024D1F2',
		check_url:'http://token.game1520.com/token/check'
	},
	server : {
		url : 'http://127.0.0.1:9090'
	},
	redis : {
		url : 'redis://127.0.0.1:6379/2'
		// password : 'srdu0l0G2eHyqP'
	},
	mysql : {
		connectionLimit : 10,
	    host            : '127.0.0.1',
	    user            : 'root',
	    password        : '123456',
	    database        : 'example'
	}
};

module.exports = Settings;