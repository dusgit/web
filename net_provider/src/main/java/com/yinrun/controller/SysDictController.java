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
import com.yinrun.model.SysDictModel;
import com.yinrun.service.SysDictService;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("dict")
public class SysDictController
{
    @Autowired
    private SysDictService sysDictService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"sys_dict:list"})
    public ResultModule list(SysDictModel dict, PageVo page)
    {
        PageResult<SysDictModel> pageResult = sysDictService.findByExample(dict, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"sys_dict:save"})
    public ResultModule add(SysDictModel dict, HttpServletRequest request)
    {
        if (StringUtil.isEmpty(dict.getCode()))
        {
            return ResultModule.error("字典编码不能为空");
        }
        if (StringUtil.isEmpty(dict.getContent()))
        {
            return ResultModule.error("字典内容不能为空");
        }
        if (StringUtil.isEmpty(dict.getType()))
        {
            return ResultModule.error("字典组名不能为空");
        }
        dict.setUpdateTime(DateUtil.getCurrentTime());
        dict.setCreateTime(DateUtil.getCurrentTime());
        if (sysDictService.insert(dict) > 0)
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
    @Power(value = {"sys_dict:update"})
    public ResultModule update(SysDictModel dict, HttpServletRequest request)
    {
        if (dict == null || dict.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        if (sysDictService.update(dict) > 0)
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
    @Power(value = {"sys_dict:delete"})
    public ResultModule delete(SysDictModel dict)
    {
        if (dict.getId() == null)
        {
            return ResultModule.error("id不能为空");
        }
        if (sysDictService.deleteById(dict.getId()) > 0)
        {
            return ResultModule.success("删除成功");
        }
        else
        {
            return ResultModule.error("删除失败");
        }
    }
}
