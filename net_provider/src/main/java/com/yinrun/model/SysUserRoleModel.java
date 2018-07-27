package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user_role")
public class SysUserRoleModel implements Serializable
{
    //
    private static final long serialVersionUID = -1590855286295842793L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long              userId;                                  // 用户id

    @Column(name = "role_id")
    private Long              roleId;                                  // 角色id

    @Column(name = "create_time")
    private Integer           createTime;

    @Column(name = "update_time")
    private Integer           updateTime;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Integer getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Integer createTime)
    {
        this.createTime = createTime;
    }

    public Integer getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime)
    {
        this.updateTime = updateTime;
    }
}
