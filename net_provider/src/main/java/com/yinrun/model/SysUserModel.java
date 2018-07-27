package com.yinrun.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_user")
public class SysUserModel implements Serializable
{

    private static final long serialVersionUID = -7463009646138238367L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String            userName;                                // 用户名称

    @Column(name = "user_pass")
    private String            userPass;                                // 用户密码

    @Column(name = "head_url")
    private String            headUrl;                                 // 用户头像

    @Column(name = "state")
    private Integer           state;                                   // 用户状态0:禁用1：正常

    @Column(name = "real_name")
    private String            realName;                                // 真实姓名

    @Column(name = "salt")
    private String            salt;

    @Column(name = "create_time") // 创建时间
    private Integer           createTime;

    @Column(name = "update_time") // 更新时间
    private Integer           updateTime;

    @Transient
    private String            roleIds;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPass()
    {
        return userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public String getHeadUrl()
    {
        return headUrl;
    }

    public void setHeadUrl(String headUrl)
    {
        this.headUrl = headUrl;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
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

    public String getRoleIds()
    {
        return roleIds;
    }

    public void setRoleIds(String roleIds)
    {
        this.roleIds = roleIds;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }
}
