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


module.exports = router;
