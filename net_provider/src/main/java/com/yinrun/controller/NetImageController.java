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
import com.yinrun.model.NetImageModel;
import com.yinrun.service.NetImageService;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping(value = "image")
public class NetImageController
{
    @Autowired
    private NetImageService netImageService;

    @ResponseBody
    @RequestMapping(value = "list")
    @Power(value = "net_image:list")
    public ResultModule list(NetImageModel item, PageVo page)
    {
        PageResult<NetImageModel> pageResult = netImageService
                .findByExample(item, page);
        Map<String, Object> results = new HashMap<>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }
    
    @ResponseBody
    @RequestMapping(value = "save")
    @Power(value = "net_image:save")
    public ResultModule add(NetImageModel item)
    {
        if (StringUtil.isEmpty(item.getUrl()))
        {
            return ResultModule.error("未上传图片");
        }
        if (item.getType() == null)
        {
            item.setType(1);
        }
        return netImageService.saveImage(item);
    }
    
    @ResponseBody
    @RequestMapping(value = "update")
    @Power(value = "net_image:update")
    public ResultModule update(NetImageModel item)
    {
        if (item == null || item.getId() == null)
        {
            return ResultModule.error("图片id不能为空");
        }
        return netImageService.updateImage(item);
    }
    
    @ResponseBody
    @RequestMapping(value = "delete")
    @Power(value = "net_image:delete")
    public ResultModule delete(NetImageModel item)
    {
        if (item == null || item.getId() == null)
        {
            return ResultModule.error("图片id不能为空");
        }
        return netImageService.deleteImage(item);
    }
}











































