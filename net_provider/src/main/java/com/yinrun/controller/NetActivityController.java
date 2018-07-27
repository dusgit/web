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
import com.yinrun.model.NetActivityModel;
import com.yinrun.service.NetActivityService;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("activity")
public class NetActivityController
{
    @Autowired
    private NetActivityService netActivityService;
    
    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"net_activity:list"})
    public ResultModule list(NetActivityModel activity, PageVo page)
    {
        PageResult<NetActivityModel> pageResult = netActivityService.findByExample(activity, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }
    
    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"net_activity:save"})
    public ResultModule add(NetActivityModel activity)
    {
        if (StringUtil.isEmpty(activity.getTitle()))
        {
            return ResultModule.error("标题不能为空");
        }
        if (activity.getState() != 0 && activity.getState() != 1)
        {
            return ResultModule.error("设置图片状态错误");
        }
        return netActivityService.addActivity(activity);
    }
    
    @ResponseBody
    @RequestMapping("update")
    @Power(value = {"net_activity:update"})
    public ResultModule update(NetActivityModel activity)
    {
        if (activity == null || activity.getId() == null)
        {
            return ResultModule.error("id为空,修改失败");
        }
        return netActivityService.updateActivity(activity);
    }
    
    @ResponseBody
    @RequestMapping("delete")
    @Power(value = {"net_activity:delete"})
    public ResultModule delete(NetActivityModel activity)
    {
        if (activity == null || activity.getId() == null)
        {
            return ResultModule.error("id为空,删除失败");
        }
        return netActivityService.deleteActivity(activity);
    }
}













































