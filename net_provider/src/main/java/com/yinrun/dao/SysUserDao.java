package com.yinrun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysUserModel;


public interface SysUserDao extends GenericMapper<SysUserModel>
{
    /**
     * 查询用户
     * @param sysUserModel
     * @return
     * @author 张亮亮
     */
    public SysUserModel findOneByEntity(SysUserModel sysUserModel);

    /**
     * 查询用户权限
     * @param id
     * @return
     * @author 张亮亮
     */
    public List<SysPowerModel> findPowerByUserId(@Param("id") Long id);

    /**
     * 查询用户角色
     * @param id
     * @return
     * @author 张亮亮
     */
    public List<SysRoleModel> findRoleByUserId(@Param("id") Long id);
}
