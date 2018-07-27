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
import com.yinrun.model.NetBannerModel;
import com.yinrun.service.NetBannerService;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("banner")
public class NetBannerController
{
    @Autowired
    private NetBannerService netBannerService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"net_loop:list"})
    public ResultModule list(NetBannerModel banner, PageVo page)
    {
        PageResult<NetBannerModel> pageResult = netBannerService
                .findByExample(banner, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"net_loop:save"})
    public ResultModule add(NetBannerModel banner)
    {
        if (StringUtil.isEmpty(banner.getImgUrl()))
        {
            return ResultModule.error("未上传图片");
        }
        if (banner.getState() != 0 && banner.getState() != 1)
        {
            return ResultModule.error("设置图片状态错误");
        }
        if (banner.getSequence() == null)
        {
            return ResultModule.error("设置图片指定排序错误");
        }
        if (banner.getType() == null)
        {
            banner.setType(1);
        }
        if (banner.getSequence() == null)
        {
            banner.setType(0);
        }
        return netBannerService.saveBanner(banner);
    }
    
    @ResponseBody
    @RequestMapping("update")
    @Power(value = {"net_loop:update"})
    public ResultModule update(NetBannerModel banner)
    {
        if (banner == null || banner.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        return netBannerService.updateBanner(banner);
    }
    
    @ResponseBody
    @RequestMapping("delete")
    @Power(value = {"net_loop:delete"})
    public ResultModule delete(NetBannerModel banner)
    {
        if (banner == null || banner.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        return netBannerService.deleteBanner(banner);
    }
}
















