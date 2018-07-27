package com.yinrun.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_role_power")
public class SysRolePowerModel implements Serializable
{


    //
    private static final long serialVersionUID = 7137377481171472018L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long              roleId;

    @Column(name = "power_id")
    private Long              powerId;

    @Column(name = "create_time") // 创建时间
    private Integer           createTime;

    @Column(name = "update_time") // 更新时间
    private Integer           updateTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getPowerId()
    {
        return powerId;
    }

    public void setPowerId(Long powerId)
    {
        this.powerId = powerId;
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
