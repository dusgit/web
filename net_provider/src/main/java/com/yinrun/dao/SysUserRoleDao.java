package com.yinrun.dao;

import org.apache.ibatis.annotations.Param;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.SysUserRoleModel;


public interface SysUserRoleDao extends GenericMapper<SysUserRoleModel>
{

    public Integer deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
