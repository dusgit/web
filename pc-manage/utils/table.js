var table = {};

table.data = [{
	name:'web_manage_user',
	chinese:'后台用户',
	fields:[{
		name:'id',
		type:'int',
	},{
		name:'user_name',
		type:'varchar'
	},{
		name:'user_pass',
		type:'varchar'
	},{
		name:'salt',
		type:'varchar'
	},{
		name:'create_time',
		type:'datetime'
	},{
		name:'update_time',
		type:'datetime'
	}]
}];

module.exports = table;