package com.yinrun.controller;

import java.util.HashMap;
import java.util.List;
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
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysUserModel;
import com.yinrun.service.SysUserService;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("user")
public class SysUserController
{

    @Autowired
    private SysUserService                   sysUserService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"sys_user:list"})
    public ResultModule list(SysUserModel user, PageVo page)
    {
        PageResult<SysUserModel> pageResult = sysUserService.findByExample(user, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("findLoginUserRoleList")
    @Power(value = {"sys_user:list"})
    public ResultModule findLoginUserRoleList(HttpServletRequest request)
    {
        List<SysRoleModel> roleList = sysUserService.findRoleByToken(request.getHeader("token"));
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", roleList);
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("findUserRoleList")
    @Power(value = {"sys_user:list"})
    public ResultModule findUserRoleList(HttpServletRequest request, SysUserModel user)
    {
        List<SysRoleModel> roleList = sysUserService.findRoleByUserId(user.getId());
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", roleList);
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"sys_user:save"})
    public ResultModule add(SysUserModel user, HttpServletRequest request)
    {
        if (StringUtil.isEmpty(user.getUserName()))
        {
            return ResultModule.error("用户名不能为空");
        }
        if (StringUtil.isEmpty(user.getUserPass()))
        {
            return ResultModule.error("用户密码不能为空");
        }
        if (StringUtil.isEmpty(user.getRealName()))
        {
            return ResultModule.error("真实名称不能为空");
        }
        if (StringUtil.isEmpty(user.getRoleIds()))
        {
            return ResultModule.error("用户角色不能为空");
        }
        return sysUserService.saveUserInfoAndPower(user, request.getHeader("token"));
    }

    @ResponseBody
    @RequestMapping("update")
    @Power(value = {"sys_user:update"})
    public ResultModule update(SysUserModel user, HttpServletRequest request)
    {
        if (user == null || user.getId() == null)
        {
            return ResultModule.error("id为空");
        }
        return sysUserService.updateUserInfoAndPower(user, request.getHeader("token"));
    }

    @ResponseBody
    @RequestMapping("delete")
    @Power(value = {"sys_user:delete"})
    public ResultModule delete(SysUserModel user)
    {
        if (user.getId() == null)
        {
            return ResultModule.error("id不能为空");
        }
        return sysUserService.deleteUserLogic(user);
    }
}
