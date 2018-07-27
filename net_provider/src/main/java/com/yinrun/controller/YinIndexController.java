package com.yinrun.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.interfaces.Power;
import com.yinrun.model.NetArticleModel;
import com.yinrun.model.NetBannerModel;
import com.yinrun.service.NetArticleService;
import com.yinrun.service.NetBannerService;

@RequestMapping(value = "/yin_index")
@Controller
public class YinIndexController
{
    @Autowired
    private NetArticleService netArticleService;

    @Autowired
    private NetBannerService netBannerService;
    
    @ResponseBody
    @RequestMapping(value = "/home")
    @Power(noPower = true)
    public ResultModule home()
    {
        PageVo page = new PageVo();
        page.setPageSize(100);
        // 查询轮播图
        NetBannerModel banner = new NetBannerModel();
        banner.setState(1);
        banner.setType(1);
        PageResult<NetBannerModel> bannerPage = netBannerService.findByExampleForNet(banner, page);
        // 查询文章列表
        NetArticleModel article = new NetArticleModel();
        page.setPageSize(4);
        article.setState(1);
        PageResult<NetArticleModel> articlePage = netArticleService.findByExample(article, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("articleList", articlePage.getList());
        results.put("bannerList", bannerPage.getList());
        return ResultModule.success("查询成功", results);
    }
}
