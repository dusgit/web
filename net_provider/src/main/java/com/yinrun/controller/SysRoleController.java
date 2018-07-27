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
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.service.SysRoleService;
import com.yinrun.utils.StringUtil;

@Controller
@RequestMapping("role")
public class SysRoleController
{

    @Autowired
    private SysRoleService sysRoleService;

    @ResponseBody
    @RequestMapping("list")
    @Power(value = {"sys_role:list"})
    public ResultModule list(SysRoleModel role, PageVo page)
    {
        PageResult<SysRoleModel> pageResult = sysRoleService.findByExample(role, page);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", pageResult.getList());
        results.put("page", pageResult.getPage());
        return ResultModule.success("查询成功", results);
    }

    @ResponseBody
    @RequestMapping("save")
    @Power(value = {"sys_role:save"})
    public ResultModule add(SysRoleModel role, HttpServletRequest request)
    {
        if (StringUtil.isEmpty(role.getRoleName()))
        {
            return ResultModule.error("角色名不能为空");
        }
        if (StringUtil.isEmpty(role.getMark()))
        {
            return ResultModule.error("角色描述不能为空");
        }
        if (StringUtil.isEmpty(role.getPowerIds()))
        {
            return ResultModule.error("角色权限不能为空");
        }
        return sysRoleService.saveRoleInfoAndPower(role);
    }

    @ResponseBody
    @RequestMapping("update")
    @Power(value = {"sys_role:update"})
    public ResultModule update(SysRoleModel role)
    {
        if (role.getId() == null)
        {
            return ResultModule.error("角色id不能为空");
        }
        return sysRoleService.updateRoleInfoAndPower(role);
    }

    @ResponseBody
    @RequestMapping("delete")
    @Power(value = {"sys_role:delete"})
    public ResultModule delete(SysRoleModel role)
    {
        if (role.getId() == null)
        {
            return ResultModule.error("id不能为空");
        }
        if (sysRoleService.deleteById(role.getId()) > 0)
        {
            return ResultModule.success("删除成功");
        }
        else
        {
            return ResultModule.error("删除失败");
        }
    }

    @ResponseBody
    @RequestMapping("findRolePowerList")
    @Power(value = {"sys_role:list"})
    public ResultModule findRolePowerList(SysRoleModel role)
    {
        List<SysPowerModel> powerList = sysRoleService.findRolePowerList(role);
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("list", powerList);
        return ResultModule.success("查询成功", results);
    }
}
