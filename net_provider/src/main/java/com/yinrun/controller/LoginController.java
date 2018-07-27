package com.yinrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinrun.bean.ResultModule;
import com.yinrun.interfaces.Power;
import com.yinrun.model.SysUserModel;
import com.yinrun.service.SysUserService;

import tk.mybatis.mapper.util.StringUtil;

@Controller
@RequestMapping("login")
public class LoginController
{

    @Autowired
    private SysUserService                   sysUserService;

    @ResponseBody
    @Power(noPower = true)
    @RequestMapping("verifyLogin")
    public ResultModule verifyLogin(SysUserModel user)
    {
        if (StringUtil.isEmpty(user.getUserName()))
        {
            return ResultModule.error("用户名称不能为空");
        }
        if (StringUtil.isEmpty(user.getUserPass()))
        {
            return ResultModule.error("用户密码不能为空");
        }
        return sysUserService.verifyLogin(user);
    }
}
