package com.yinrun.dao;

import org.apache.ibatis.annotations.Param;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.SysRolePowerModel;


public interface SysRolePowerDao extends GenericMapper<SysRolePowerModel>
{
    public Integer deleteByRoleIdAndPowerId(@Param("roleId") Long roleId, @Param("powerId") Long powerId);
}
