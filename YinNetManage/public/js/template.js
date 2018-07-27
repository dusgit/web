/*
var opts = {
    width:600,//弹框宽度
    height:600,//弹框高度
    title : '权限管理',
	fields:[
		{
            name  : '角色id',   // 字段名称
            field : 'id',       // 字段
            query : false,      // 字段是否支持搜索  true：是，false：否
            type  : 'hidden'    // 字段类型 hidden：不显示，image:图片，select：下拉框，input：普通字符串 ,time:时间类型
            dict  : [{key:'',value:'全部'},{key:'1',value:'正常'},{key:'0',value:'禁用'}]  // 下拉框内容 
            add   : 'show'  //新增页面是否显示  'show'：显示，'hidden'：不显示  ，默认为'show'
            edit   : 'show'  //编辑页面是否显示  'show'：显示，'hidden'：不显示，'disabled'：不可以编辑  ，默认为'show'
            addHtml:''//自定义新增显示内容
            editHtml:''//自定义编辑显示内容
		}
	],
	query_btns:[{
                    name:'查询',         // 查询区域显示按钮名称
                    click:'findListPage' // 点击按钮回调函数
                    power :'sys_dict:list'//需要的权限
                },
                {
                    name:'新增', // 查询区域显示按钮名称
                    click:'add'  // 点击按钮回调函数
                    power :'sys_dict:save'//需要的权限
                }],
	action_btns:[{
                    name:'编辑', //查询列表内显示按钮名称
                    click:'edit' //点击按钮回调函数，默认回传id值
                },
                {
                    name:'删除',      //查询列表内显示按钮名称
                    click:'deleteUser'//点击按钮回调函数，默认回传id值
                }],
    add  : {
			addSave : addSave,  //保存函数
			pop_window_width:1000, //弹出框宽度
			pop_window_height:800,//弹出框高度
			cb : addKindEditor //弹出框后回调函数
		   },
	edit : {
			editSave : editSave,
			pop_window_width:600,
			pop_window_height:600
		   }
}
*/
var template = {
    opts : null
}
function initTemplate(opts,cb){
    template.opts = opts;
    $('#work_area').html($('#template1').html());
    $('#template1').html('');
    //初始化查询相关
    $('body').append('<input type="hidden" id="queryData"/>');
    var query_html = '';
    $.each(opts.fields,function(idx,ele){
        if(ele.query){
            if(ele.type == 'input'){
                query_html += '<div class="query_item"><span class="query_sp">'+ele.name+'</span><input type="text" id="query_param_'+ele.field+'" data_field="'+ele.field+'" class="query_param query_ipt"/></div>';
            }else if(ele.type == 'select'){
                query_html += '<div class="query_item"><span class="query_sp">'+ele.name+'</span><select class="query_param query_slt" id="query_param_'+ele.field+'"  data_field="'+ele.field+'">';
                $.each(ele.dict,function(index,element){
                    query_html += '<option value="'+element['key']+'">'+element['value']+'</option>'
                });
                query_html += '</select></div>';
            }
        }
    });
    var powerMap = $('body').data('powerMap');
    $.each(opts.query_btns,function(idx,ele){
        //判断权限
        if(powerMap.hasOwnProperty(ele.power) || ele.power == 'nopower'){
            query_html += '<div class="query_btn_item"><button class="query_btn" onclick="'+ele.click+'()">'+ele.name+'</button></div>';
        }
    });
    $('#query_c').html(query_html);
    //初始化新增
    if(opts.add){
        var add_html = '';
        $.each(opts.fields,function(idx,ele){
            var showadd = ele.add || 'show';
            if(showadd == 'show'){
                if(ele.addHtml){
                    add_html += '<li style="margin-top:10px;"><span class="pop_edt_sp">'+ele.name+'</span>'
                    add_html += ele.addHtml+'</li>';
                }else{
                    if(ele.type == 'select'){
                        add_html += '<li><span class="pop_edt_sp">'+ele.name+'</span>'
                        add_html += '<select class="f_p_a pop_edt_ipt" field="'+ele.field+'" id="pop_add_'+ele.field+'">'
                        $.each(ele.dict,function(index,element){
                            add_html += '<option value="'+element.key+'">'+element.value+'</option>';
                        });
                        add_html += '</select></li>'
                    }else{
                        add_html += '<li><span class="pop_edt_sp">'+ele.name+'</span>'
                             +  '<input type="text" id="pop_add_'+ele.field+'" placeholder="'+ele.name+'" field="'+ele.field+'" class="f_p_a pop_edt_ipt"/>'
                             +  '</li>';
                    }
                }
            }
        });
        $('#template1_pop_add_ul').html(add_html);
        $('#template1_pop_add_btn').attr('onclick','template_add_save()');
        cb && cb();
    }
    if(opts.edit){
        var edit_html = '';
        $.each(opts.fields,function(idx,ele){
            var showedit = ele.edit || 'show';
            if(showedit == 'show'){
                if(ele.editHtml){
                    edit_html += '<li style="margin-top:10px;"><span class="pop_edt_sp">'+ele.name+'</span>'
                    edit_html += ele.editHtml+'</li>';
                }else{
                    if(ele.type == 'select'){
                        edit_html += '<li><span class="pop_edt_sp">'+ele.name+'</span>'
                        edit_html += '<select class="f_p_e pop_edt_ipt" field="'+ele.field+'" id="pop_edt_'+ele.field+'">'
                        $.each(ele.dict,function(index,element){
                            edit_html += '<option value="'+element.key+'">'+element.value+'</option>';
                        });
                        edit_html += '</select></li>'
                    }else{
                        edit_html += '<li><span class="pop_edt_sp">'+ele.name+'</span>'
                             +  '<input type="text" id="pop_edt_'+ele.field+'" placeholder="'+ele.name+'" field="'+ele.field+'" class="f_p_e pop_edt_ipt"/>'
                             +  '</li>';
                    }
                }
            }else if(showedit == 'disabled'){
                //不可以编辑
                edit_html += '<li><span class="pop_edt_sp">'+ele.name+'</span>'
                             +  '<input disabled type="text" id="pop_edt_'+ele.field+'" placeholder="'+ele.name+'" field="'+ele.field+'" class="f_p_e pop_edt_ipt"/>'
                             +  '</li>';
            }
        });
        $('#template1_pop_edt_ul').html(edit_html);
        $('#template1_pop_edt_btn').attr('onclick','template_edit_save()');
        cb && cb();
    }
}

function template_add(){
	if(!$('body').data('template1_pop_add')){
        $('body').data('template1_pop_add',$('#template1_pop_add').html());
        $('#template1_pop_add').html('');
	}
	var opts={
		width:template.opts.add.pop_window_width || 600,
		height:template.opts.add.pop_window_height || 600,
		title:'新增 . '+template.opts.title,
		html:$('body').data('template1_pop_add')
	}
	showPopWindow(opts,function(){
        if(template.opts.add && template.opts.add.cb){
            template.opts.add['cb']();
        }
    });
}

function template_add_save(){
    var param = {};
    $('.f_p_a').each(function(idx,ele){
        param[$(ele).attr('field')] = $(ele).val();
    });
    template.opts.add.addSave(param);
}
function template_edit(id){
	if(!$('body').data('template1_pop_edt')){
        $('body').data('template1_pop_edt',$('#template1_pop_edt').html());
        $('#template1_pop_edt').html('');
	}
	var opts={
		width:template.opts.edit.pop_window_width || 600,
		height:template.opts.edit.pop_window_height || 600,
		title:'编辑 . '+template.opts.title,
		html:$('body').data('template1_pop_edt')
    }
	showPopWindow(opts,function(){
        var item = getDataById(id);
        $('.f_p_e').each(function(idx,ele){
            $(ele).val(item[$(ele).attr('field')]);
        });
        if(template.opts.edit && template.opts.edit.cb){
            template.opts.edit['cb'](id);
        }
    });
}
function template_edit_save(){
    var param = {};
    $('.f_p_e').each(function(idx,ele){
        param[$(ele).attr('field')] = $(ele).val();
    });
    template.opts.edit.editSave(param);
}
function getDataById(id){
    var resData = {};
    $.each(template.list,function(idx,ele){
        if(ele.id == id){
            resData = ele;
        }
    });
    return resData;
}
function formatLength(str){
    if((str+'').length < 2){
        str = '0'+str;
    }
    return str;
}
function formatTime(time){
    var date = new Date(time * 1000);
    return date.getFullYear() + '-' 
            + formatLength(date.getMonth() + 1) + '-' 
            + formatLength(date.getDate()) + ' ' 
            + formatLength(date.getHours()) + ':' 
            + formatLength(date.getMinutes()) + ':'
            + formatLength(date.getSeconds());
}
function showTemplate(opts,data,getListPage){
    console.log('重新渲染');
    template.getListPage = getListPage;
    template.page = data.page||{};
    template.list = data.list;
    var data_html = '<table class="query_tb" cellspacing="0" cellpadding="0" >'
    data_html += '<thead><tr>';
    data_html += '<td class="w80">序号</td>';
    $.each(opts.fields,function(idx,ele){
        if(ele.type == 'hidden'){
            return true;
        }
        data_html += '<td>'+ele['name']+'</td>';
    });
    if(!!opts.action_btns && opts.action_btns.length > 0){
        data_html += '<td>操作</td>';
    }
    data_html += '</tr></thead>'
    //tbody
    data_html += '<tbody>';
    $.each(data.list,function(idx,ele){
        data_html += '<tr>';
        data_html += '<td>'+((template.page.currentPage - 1) * template.page.pageSize + idx + 1)+'</td>'
        $.each(opts.fields,function(index,element){
            if(element.type == 'hidden'){
                return true;
            }
            data_html += '<td>';
            if(element['type'] == 'select'){
                data_html += getDictValue(element['dict'],ele[element['field']]);
            }else if(element['type'] == 'image'){
                data_html += '<img src="'+ele[element['field']]+'" style="height:80px;"></img>';
            }else if(element['type'] == 'time'){
                console.log(ele);
                data_html += formatTime(ele[element['field']]);
            }else{
                data_html += ele[element['field']] || '-';
            }
            data_html += '</td>';
        });
        if(!!opts.action_btns && opts.action_btns.length > 0){
            data_html += '<td>';
            var powerMap = $('body').data('powerMap');
            $.each(opts.action_btns,function(index,element){
                if(powerMap.hasOwnProperty(element.power) || element.power == 'nopower'){
                    data_html += '<button class ="action_btn" onclick="'+element.click+'('+ele.id+')">'+element.name+'</button>';
                }
            });
            data_html += '</td>';
        }
        data_html += '</tr>';
    });
    data_html += '</tbody></table>';
    data_html += getPageHtml(data.page || {},getListPage);
    $('#query_d').html(data_html);
}
function getQueryParam(){
    var param = {};
    $.each($('.query_param'),function(idx,ele){
        if(!!$(ele).val()){
            param[$(ele).attr('data_field')] = $(ele).val();
        }
    })
    return param;
}
function getDictValue(dict,key){
    var resopts = '';
    $.each(dict,function(idx,ele){
        if(ele['key'] == key){
            resopts = ele['value'];
        }
    });
    return resopts;
}

function getPageHtml(page,getListPage){
    var page_html = '<div class="query_page">';
    var currentPage = page.currentPage || 1;
    var hasNextPage = page.hasNextPage || false;
    var hasPreviousPage = page.hasPreviousPage || false;
    var pageSize = page.pageSize || 10;
    var totalPage = page.totalPage || 1;
    if(hasPreviousPage){
        page_html += '<button class="page_btn page_btn_valid" onclick="prePage()">上一页</button>';
    }else{
        page_html += '<button class="page_btn page_btn_invalid">上一页</button>';
    }
    if(totalPage < 10 ){//全显
        //123456789
        for(var i = 1;i < (totalPage + 1);i++){
            page_html += getPageSp(i,currentPage,1);
        }
    }else if(currentPage < 4){
        //123456...15
        for(var i = 1;i < 6;i++){
            page_html += getPageSp(i,currentPage,1);
        }
        page_html += getPageSp(null,currentPage,2);
        page_html += getPageSp(totalPage,currentPage,1);
    }else if(currentPage > (totalPage - 5)){
        //1...11 12 13 14 15
        page_html += getPageSp(1,currentPage,1);
        page_html += getPageSp(null,currentPage,2);
        for(var i = (totalPage - 5); i < totalPage+1; i++){
            page_html += getPageSp(i,currentPage,1);
        }
    }else{
        //1,...789...15
        page_html += getPageSp(1,currentPage,1);
        page_html += getPageSp(null,currentPage,2);
        for(var i = (currentPage - 2); i < (currentPage + 2); i++){
            page_html += getPageSp(i,currentPage,1);
        }
        page_html += getPageSp(null,currentPage,2);
        page_html += getPageSp(totalPage,currentPage,1);
    }
    if(hasNextPage){
        page_html += '<button class="page_btn page_btn_valid" onclick="nextPage()">下一页</button>';
    }else{
        page_html += '<button class="page_btn page_btn_invalid">下一页</button>';
    }
    page_html +='</div>';
    return page_html;
}
function getTemplageListPage(page){
    template.getListPage(page);
}
function prePage(){
    getTemplageListPage(template.page.currentPage - 1);
}
function nextPage(){
    getTemplageListPage(template.page.currentPage + 1);
}
function getPageSp(i,currentPage,type){
    if(type == 1){
        var sty = '';
        if(i == currentPage){
            sty = 'page_cur';
        }
        return '<span class="page_sp '+sty+'" onclick="getTemplageListPage('+i+')">'+i+'</span>';
    }else if(type == 2){
        return '<span class="page_sp">...</span>';
    }
}