const crypto = require('crypto');

var CRYPTO = {};

CRYPTO.deCode = function(content,salt){
	const decipher = crypto.createDecipher('aes192', salt);
	var decrypted = '';
	try{
		decrypted = decipher.update(content, 'hex', 'utf8');
		decrypted += decipher.final('utf8');
	}catch(e){
		
	}
	return decrypted;
}

CRYPTO.enCode = function enCode(content,salt){
	const cipher = crypto.createCipher('aes192', salt);
	let encrypted = cipher.update(content, 'utf8', 'hex');
	encrypted += cipher.final('hex');
	console.log(encrypted);
	return encrypted;
}

CRYPTO.getClientIp = function getClientIp(req){
	return req.headers['x-forwarded-for'] || req.connection.remoteAddress || req.socket.remoteAddress || req.connection.socket.remoteAddress;
}

module.exports = CRYPTO;


