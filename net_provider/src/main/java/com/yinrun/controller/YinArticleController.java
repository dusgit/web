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
import com.yinrun.service.NetArticleService;

@RequestMapping(value = "/yin_article")
@Controller
public class YinArticleController
{
    @Autowired
    private NetArticleService netArticleService;

    @ResponseBody
    @RequestMapping(value = "/detail")
    @Power(noPower = true)
    public ResultModule detail(Long id)
    {
        if (id == null)
        {
            return ResultModule.error("id不能为空");
        }
        return netArticleService.findArticleDetail(id);
    }

    @ResponseBody
    @RequestMapping("list")
    @Power(noPower = true)
    public ResultModule list(NetArticleModel article, PageVo page)
    {
        article.setState(1);
        PageResult<NetArticleModel> pageResult = netArticleService.findByExample(article, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }
}
