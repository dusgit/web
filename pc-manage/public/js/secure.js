$(document).ready(function(){
	verifyLogin();
});

function verifyLogin(){
	var token = getCookie('token');
	var url = window.location.href;
	if(url.indexOf('login') > 0){
		//已经是登录页面
	}else{
		if(!token){
			window.location.href='/login.html';
		}
	}
}