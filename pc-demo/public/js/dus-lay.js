/**
 *布局
 */
function autoLay(id,param){
	var settings = {
		mg : 10
	}
	if(param && param.mg){
		settings.mg = param.mg
	}
	var baseEle = null;
	if(typeof id == 'string'){
		baseEle = $('#'+id);
	}else{
		baseEle = id;
	}
	
	var baseW = baseEle.width();
	var baseH = baseEle.height();
	
	var borderW = getBorderWidth(baseEle);
	var borderH = getBorderHeight(baseEle);
	baseW = baseW - borderW;
	baseH = baseH - borderH;
	
	var autoWidth  = baseW;
	var autoHeight = baseH;
	var childCount = $('#wd').children().length;
	
	var coor = 'x';
	$.each(baseEle.children(),function(idx,ele){
		var laySize = $(ele).attr('layx');
		if(!laySize){
			laySize = $(ele).attr('layy');
			coor = 'y';
		}
		if(laySize){
			var layWidth = laySize.split('x')[0];
			var layHeight = laySize.split('x')[1];
			
			layWidth = layWidth == 'null' ?'':layWidth;
			layHeight = layHeight == 'null' ?'':layHeight;
			var opts = {};
			if('x' == coor){
				opts['float'] = 'left';
				if(idx != 0){
					opts['margin-left'] = settings['mg'] + 'px';
					autoWidth = autoWidth - settings['mg'];
				}
				if(layWidth){
					var borderWidth = getBorderWidth(ele);
					if(borderWidth > 0){
						autoWidth =  autoWidth - borderWidth;
					}
					if(layWidth == 'auto'){
						opts['width'] = autoWidth + 'px';
					}else{
						autoWidth = autoWidth - layWidth;
						opts['width'] = layWidth + 'px';
					}
				}
				if(layHeight){
					var borderHeight = getBorderHeight(ele);
					if(borderHeight > 0){
						autoHeight =  autoHeight - borderHeight;
					}
					if(layHeight == 'auto'){
						opts['height'] = autoHeight + 'px';
					}else{
						autoHeight = autoHeight - layHeight;
						opts['height'] = layHeight + 'px';
					}
				}
			}else{
				if(idx != 0){
					opts['margin-top'] = settings['mg'] + 'px';
					autoHeight = autoHeight - settings['mg'];
				}
				if(layWidth){
					if(layWidth == 'auto'){
						opts['width'] = autoWidth + 'px';
					}else{
						autoWidth = autoWidth - layWidth;
						opts['width'] = layWidth + 'px'; 
					}
				}
				if(layHeight){
					var borderHeight = getBorderHeight(ele);
					if(borderHeight > 0){
						autoHeight =  autoHeight - borderHeight;
					}
					if(layHeight == 'auto'){
						opts['height'] = autoHeight + 'px';
					}else{
						autoHeight = autoHeight - layHeight;
						opts['height'] = layHeight + 'px'; 
					}
				}
			}
			$(ele).css(opts);
			
			var subChildCount = $(ele).children().length;
			if(subChildCount > 0){
				autoLay($(ele),param);
			}
		}
	});
	
	
	function getBorderWidth(ele){
		var borderL = parseInt($(ele).css('border-left-width'));
		var borderR = parseInt($(ele).css('border-right-width'));
		return borderL + borderR;
	}
	
	function getBorderHeight(ele){
		var borderT = parseInt($(ele).css('border-top-width'));
		var borderB = parseInt($(ele).css('border-bottom-width'));
		return borderT + borderB;
	}
}
