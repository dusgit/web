//提示框
function showTip(message){
	var html = '<div class="popTip" onclick="hideTip()" style="position:absolute;width:500px;height:100px;z-index:9999;top:50%;left:50%;margin:-50px 0px 0px -250px;border-radius:6px;overflow:hidden;">';
	html += '<div style="position:absolute;width:100%;height:100%;background-color:#000;z-index:2;filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;opacity: 0.5;"></div>';
	html += '<div style="position:absolute;width:490px;height:90px;margin:5px;background-color:#fff;border-radius:6px;line-height:90px;text-align:center;z-index:3;">'+message+'</div>';
	html += '</div>';
	$('body').append(html);
	setTimeout(function(){
		$('.popTip').remove();
	},1500);
}
//确认框
function showConfirm(message,cb_sure,cb_cancel){
    var html = '<div class="pop_confirm" style="position:absolute;width:500px;height:140px;z-index:9999;top:50%;left:50%;margin:-50px 0px 0px -250px;border-radius:6px;overflow:hidden;">';
	html += '<div style="position:absolute;width:100%;height:100%;background-color:#000;z-index:2;filter:alpha(opacity=30);-moz-opacity:0.3;-khtml-opacity: 0.3;opacity: 0.3;"></div>';
    html += '<div style="position:absolute;width:490px;height:130px;margin:5px;background-color:#fff;border-radius:6px;line-height:90px;text-align:center;z-index:3;">';
    html += '<div style="position:absolute;width:100%;height:90px;top:0px;left:0px;">'+message+'</div>';
    html += '<div style="position:absolute;width:100%;height:40px;top:90px;left:0px;border-top:1px solid #ddd;">';
    html += '<span class="pop_confirm_sure" style="position:absolute;width:250px;height:40px;background-color:#fff;color:#000;left:0px;top:0px;cursor:pointer;line-height:40px;">确&nbsp;&nbsp;定</span>';
    html += '<span class="pop_confirm_cancel" style="position:absolute;width:250px;height:40px;background-color:#fff;left:240px;border-left:1px solid #ddd;cursor:pointer;top:0px;line-height:40px;">取&nbsp;&nbsp;消</span>';
    html += '</div>';
    html += '</div>';
	html += '</div>';
    $('body').append(html);
    $('.pop_confirm_sure').click(function(){
        $('.pop_confirm').remove();
        cb_sure && cb_sure();
    });
    $('.pop_confirm_cancel').click(function(){
        $('.pop_confirm').remove();
        cb_cancel && cb_cancel();
    });
}
//弹出窗
function showPopWindow(opts,cb){
    if($('#popWindow').length > 0){
        return;
    }
    var all_w = document.body.clientWidth;
    var all_h = document.body.clientHeight;
    var w =  $(window).width();
    var h =  $(window).height();
    var width = opts.width || 600;
    var height = opts.height || 600;
    var html = '<div id="popWindow" style="position:absolute;width:'+all_w+'px;height:'+(all_h > h ? all_h : h)+'px;left:0px;top:0px;z-index:0;">'
             + '<div class="pop_bg" onclick="closePopWindow(event)" style="position:absolute;width:'+all_w+'px;height:'+(all_h > h ? all_h : h)+'px;left:0px;top:0px;background-color:#000;z-index:998;filter:alpha(opacity=30);-moz-opacity:0.3;-khtml-opacity: 0.3;opacity: 0.3;"></div>'
             + '<div class="pop_body" style="position:absolute;height:'+height+'px;width:'+width+'px;left:50%;top:'+h/2+'px;border-radius:10px;overflow:hidden;;margin:-'+(height/2)+'px 0px 0px -'+(width/2)+'px;background-color:#fff;z-index:999;">'
             + '<div style="position:absolute;top:0px;width:100%;height:30px;border-bottom:1px solid #ddd;background-color:#089ad4;color:#fff;"><span style="float:left;line-height:30px;text-indent:10px;">'+opts['title']+'</span><span style="float:right;width:60px;height:30px;border-left:1px solid #089ad4;line-height:30px;text-align:center;cursor:pointer;" onclick="closePopWindow()" >关闭</span></div>'
             + '<div style="position:absolute;width:'+width+'px;height:'+(height - 30)+'px;top:30px;overflow:hidden;left:0px;z-index:9999;">'
             + (opts.html||'')
             + '</div>'
             + '</div>';
    $('body').append(html);
    cb && cb();
}
$(document).keyup(function(event){
    if (event.keyCode === 27 && $("#popWindow")) {
        closePopWindow();
    }
});
function closePopWindow(num){
    num = num || 0;
    setTimeout(function(){
        $('#popWindow').remove();
    },num);
}
function hideTip(){
	$('.popTip').remove();
}
function setCookie(name,value,timeout){
    document.cookie = name+'='+value+';expires='+new Date(new Date().getTime() + timeout*1000).toGMTString();
}
function deleteCookie(name){
    document.cookie = name+'='+';expires='+new Date(new Date().getTime()).toGMTString();
}
function getCookie(cookieName) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for(var i = 0; i < arrCookie.length; i++){
        var arr = arrCookie[i].split("=");
        if(cookieName == arr[0]){
            return arr[1];
        }
    }
    return "";
}
function getCheckValue(name){
	var vals = [];
	$.each($("input[name='"+name+"']:checked"),function(idx,ele){
		vals.push($(ele).attr('value'));
	});
	return vals.join(',');
}
function isActionLock(action){
    if($('body').data(action) && $('body').data(action) > 0){
        return true;
    }else{
        return false;
    }
}
function lockAction(action){
    $('body').data(action,1);
}
function unlockAction(action){
    $('body').data(action,'');
}