package com.yinrun.bean;

import java.io.Serializable;
import java.util.Date;

public class LoginVo implements Serializable
{
    private static final long serialVersionUID = 3929673273740890425L;

    private String userName;

    private Long   id;

    private Date   expireTime;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }
}
