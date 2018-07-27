package com.yinrun.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("article")
public class NetArticleController
{
    @Autowired
    private NetArticleService netArticleService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"net_article:list"})
    public ResultModule list(NetArticleModel article, PageVo page)
    {
        PageResult<NetArticleModel> pageResult = netArticleService.findByExample(article, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"net_article:save"})
    public ResultModule add(NetArticleModel article, HttpServletRequest request)
    {
        if (StringUtil.isEmpty(article.getTitle()))
        {
            return ResultModule.error("标题不能为空");
        }
        if (StringUtil.isEmpty(article.getContent()))
        {
            return ResultModule.error("内容不能为空");
        }
        if (article.getType() == null)
        {
            return ResultModule.error("文章类型不能为空");
        }
        if (article.getState() == null)
        {
            return ResultModule.error("文章状态不能为空");
        }
        article.setId(null);
        article.setValidFlag(1);
        article.setAuthor("");
        article.setHits(article.getHits() == null ? 0 : article.getHits());
        article.setCreateTime(DateUtil.getCurrentTime());
        article.setUpdateTime(DateUtil.getCurrentTime());
        if (netArticleService.insert(article) > 0)
        {
            return ResultModule.success("保存成功");
        }
        else
        {
            return ResultModule.error("保存失败");
        }
    }

    @ResponseBody
    @RequestMapping("update")
    @Power(value = {"net_article:update"})
    public ResultModule update(NetArticleModel article, HttpServletRequest request)
    {
        if (article == null || article.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        NetArticleModel nam = netArticleService.findById(article.getId());
        if (nam == null)
        {
            return ResultModule.error("文章不存在");
        }
        if (StringUtil.isNotEmpty(article.getTitle()) && !article.getTitle().equals(nam.getTitle()))
        {
            nam.setTitle(article.getTitle());
        }
        nam.setContent(article.getContent());
        if (article.getType() != null && !article.getType().equals(nam.getType()))
        {
            nam.setType(article.getType());
        }
        if (article.getState() != null && !article.getState().equals(nam.getState()))
        {
            nam.setState(article.getState());
        }
        if (article.getHits() != null && !article.getHits().equals(nam.getHits()))
        {
            nam.setHits(article.getHits());
        }
        if (article.getSequence() != null && !article.getSequence().equals(nam.getSequence()))
        {
            nam.setSequence(article.getSequence());
        }
        nam.setUpdateTime(DateUtil.getCurrentTime());
        if (netArticleService.update(nam) > 0)
        {
            return ResultModule.success("更新成功");
        }
        else
        {
            return ResultModule.error("更新失败");
        }
    }

    @ResponseBody
    @RequestMapping("delete")
    @Power(value = {"net_article:delete"})
    public ResultModule delete(NetArticleModel article)
    {
        if (article.getId() == null)
        {
            return ResultModule.error("id不能为空");
        }
        NetArticleModel nam = netArticleService.findById(article.getId());
        nam.setValidFlag(0);
        if (netArticleService.update(nam) > 0)
        {
            return ResultModule.success("删除成功");
        }
        else
        {
            return ResultModule.error("删除失败");
        }
    }
}
