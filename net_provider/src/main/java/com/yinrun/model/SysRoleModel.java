package com.yinrun.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_role")
public class SysRoleModel implements Serializable
{


    //
    private static final long serialVersionUID = 3949996356707868668L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String            roleName;                               // 角色

    @Column(name = "mark")
    private String            mark;                                   // 角色描述

    @Column(name = "valid_flag")
    private Integer           validFlag;                              // 有效标志
                                                                      // 0:无效
                                                                      // 1：有效

    @Column(name = "create_time") // 创建时间
    private Integer           createTime;

    @Column(name = "update_time") // 更新时间
    private Integer           updateTime;

    @Transient
    private String            powerIds;
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public Integer getValidFlag()
    {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag)
    {
        this.validFlag = validFlag;
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

    public String getPowerIds()
    {
        return powerIds;
    }

    public void setPowerIds(String powerIds)
    {
        this.powerIds = powerIds;
    }

}
