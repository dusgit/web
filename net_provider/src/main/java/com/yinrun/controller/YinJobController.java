package com.yinrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinrun.bean.ResultModule;
import com.yinrun.interfaces.Power;
import com.yinrun.service.NetJobService;

@RequestMapping(value = "/yin_job")
@Controller
public class YinJobController
{
    @Autowired
    private NetJobService netJobService;



    @ResponseBody
    @RequestMapping("list")
    @Power(noPower = true)
    public ResultModule list()
    {
        return netJobService.findJobListForNet();
    }
}
