var express = require('express');
var router = express.Router();
var articleService = require('../service/articleService');
var userService = require('../service/userService');
var roleService = require('../service/roleService');
var powerService = require('../service/powerService');
var bannerService = require('../service/bannerService');
var activityService = require('../service/activityService');
var jobService = require('../service/jobService');
var dictService = require('../service/dictService');
var imageService = require('../service/imageService');
var tools = require('../utils/tools');
/* 页面跳转路由 */
//首页
router.get('/index.html', function (req, res, next) {
	res.render('index', res.results);
});
//用户管理页面
router.get('/sys_user_list.html', function (req, res, next) {
	res.render('user',tools._.extend(res.results,{titleTip:'用户管理'}));
});
//角色管理页面
router.get('/sys_role_list.html', function (req, res, next) {
	res.render('role',tools._.extend(res.results,{titleTip:'角色管理'}));
});
//权限管理页面
router.get('/sys_power_list.html', function (req, res, next) {
	res.render('power',tools._.extend(res.results,{titleTip:'权限管理'}));
});
//首页轮播图管理
router.get('/net_loop_list.html', function (req, res, next) {
	res.render('banner',tools._.extend(res.results,{titleTip:'首页轮播图'}));
});
//新闻动态管理页面
router.get('/net_news_list.html', function (req, res, next) {
	res.render('net_news_list',tools._.extend(res.results,{titleTip:'新闻动态'}));
});
//员工活动管理页面
router.get('/net_activity_list.html', function (req, res, next) {
	res.render('activity',tools._.extend(res.results,{titleTip:'员工活动'}));
});
//招聘岗位管理页面
router.get('/net_job_list.html', function (req, res, next) {
	res.render('net_job_list',tools._.extend(res.results,{titleTip:'招聘职位'}));
});
//字典管理页面
router.get('/sys_dict_list.html', function (req, res, next) {
	res.render('sys_dict_list',tools._.extend(res.results,{titleTip:'字典管理'}));
});
//图片管理页面
router.get('/net_image_list.html', function (req, res, next) {
	res.render('image',tools._.extend(res.results,{titleTip:'图片管理'}));
});
/* 数据跳转路由 */
router.get('/findUserListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/findCurrentRoleList',function(req,res,next){
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.findCurrentRoleList(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/findUserRoleList',function(req,res,next){
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.findUserRoleList(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/addUser', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.addUser(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/deleteUser', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.deleteUser(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/updateUser', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	userService.updateUser(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/getArticle', function (req, res, next) {
	articleService.getArticle(req.query || {}, function (results) {
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/addNetNews', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	articleService.addNetNews(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/updateNetNews', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	articleService.updateNetNews(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/deleteNetNews', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	articleService.deleteNetNews(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/findRoleListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	roleService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/addRole', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	roleService.addRole(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/updateRole', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	roleService.updateRole(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/deleteRole', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	roleService.deleteRole(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/findRolePowerList',function(req,res,next){
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	roleService.findRolePowerList(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
//权限管理
router.get('/findPowerListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/plain;charset=utf-8'});
	powerService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
//文章查询
router.get('/findArticleListPage',function(req, res, next){
	res.writeHead(200,{'Content-Type':'text/plain;charset=utf-8'});
	articleService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
//岗位查询
router.get('/findJobListPage',function(req, res, next){
	res.writeHead(200,{'Content-Type':'text/plain;charset=utf-8'});
	jobService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/addNetJob', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	jobService.addNetJob(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/updateNetJob', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	jobService.updateNetJob(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/deleteNetJob', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	jobService.deleteNetJob(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
//字典管理
router.get('/findDictListPage',function(req, res, next){
	res.writeHead(200,{'Content-Type':'text/plain;charset=utf-8'});
	dictService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/addSysDict', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	dictService.addSysDict(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/updateSysDict', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	dictService.updateSysDict(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.post('/deleteSysDict', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	dictService.deleteSysDict(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});
router.get('/findBannerListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	bannerService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.get('/uploadImg', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	bannerService.uploadImg(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/addBanner', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	bannerService.addBanner(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/updateBanner', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	bannerService.updateBanner(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/deleteBanner', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	bannerService.deleteBanner(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.get('/findActivityListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	activityService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/addActivity', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	activityService.addActivity(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.get('/findActivityImagesList', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	activityService.findActivityImagesList(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/updateActivity', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	activityService.updateActivity(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/deleteActivity', function (req, res, next) {
	console.log("删除1");
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	activityService.deleteActivity(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.get('/findImageListPage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	imageService.findListPage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/addImage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	imageService.addImage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/updateImage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	imageService.updateImage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/deleteImage', function (req, res, next) {
	res.writeHead(200,{'Content-Type':'text/html;charset=utf-8'});
	imageService.deleteImage(req,function(results){
		if (typeof results == 'object') {
			res.json(results);
		} else {
			res.end(results);
		}
	});
});

router.post('/uploadImage',function(req,res,next){
	res.end();
});
module.exports = router;
