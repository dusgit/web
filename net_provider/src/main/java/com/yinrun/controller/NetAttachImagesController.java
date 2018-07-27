package com.yinrun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinrun.bean.ResultModule;
import com.yinrun.interfaces.Power;
import com.yinrun.model.NetAttachImagesModel;
import com.yinrun.service.NetAttachImagesService;

@Controller
@RequestMapping("attachImages")
public class NetAttachImagesController
{
    @Autowired
    private NetAttachImagesService netAttachImagesService;
    
    @ResponseBody
    @RequestMapping("list")
    @Power(noPower=true)
    public ResultModule list(Long targetId, String type)
    {
        List<NetAttachImagesModel> list = netAttachImagesService.list(targetId, type);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", list);
        return ResultModule.success("查询成功", results);
    }
}
