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
import com.yinrun.model.SysPowerModel;
import com.yinrun.service.SysPowerService;

@Controller
@RequestMapping("power")
public class SysPowerController
{

    @Autowired
    private SysPowerService sysPowerService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"sys_power:list"})
    public ResultModule list(SysPowerModel role, PageVo page)
    {
        PageResult<SysPowerModel> pageResult = sysPowerService.findByExample(role, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

}
