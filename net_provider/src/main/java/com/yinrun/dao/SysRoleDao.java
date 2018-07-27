package com.yinrun.dao;

import java.util.List;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysUserRoleModel;


public interface SysRoleDao extends GenericMapper<SysRoleModel>
{

    public List<SysUserRoleModel> findUserRoleByRoleId(Long roleId);

    public List<SysPowerModel> findPowerByRoleId(Long roleId);

}
