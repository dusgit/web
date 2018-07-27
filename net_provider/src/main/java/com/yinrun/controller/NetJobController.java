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
import com.yinrun.model.NetJobModel;
import com.yinrun.service.NetJobService;
import com.yinrun.service.SysDictService;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("job")
public class NetJobController
{
    @Autowired
    private NetJobService netJobService;

    @Autowired
    private SysDictService sysDictService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"net_job:list"})
    public ResultModule list(NetJobModel job, PageVo page)
    {
        PageResult<NetJobModel> pageResult = netJobService.findByExample(job, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"net_job:save"})
    public ResultModule add(NetJobModel job, HttpServletRequest request)
    {
        if (StringUtil.isEmpty(job.getPosition()))
        {
            return ResultModule.error("岗位名称不能为空");
        }
        if (job.getType() == null)
        {
            return ResultModule.error("部门不能为空");
        }
        if (StringUtil.isEmpty(job.getContent()))
        {
            return ResultModule.error("岗位描述不能为空");
        }
        job.setUpdateTime(DateUtil.getCurrentTime());
        job.setCreateTime(DateUtil.getCurrentTime());
        if (netJobService.insert(job) > 0)
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
    @Power(value = {"net_job:update"})
    public ResultModule update(NetJobModel job, HttpServletRequest request)
    {
        if (job == null || job.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        if (netJobService.update(job) > 0)
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
    @Power(value = {"net_job:delete"})
    public ResultModule delete(NetJobModel job)
    {
        if (job.getId() == null)
        {
            return ResultModule.error("id不能为空");
        }
        if (netJobService.deleteById(job.getId()) > 0)
        {
            return ResultModule.success("删除成功");
        }
        else
        {
            return ResultModule.error("删除失败");
        }
    }
}
