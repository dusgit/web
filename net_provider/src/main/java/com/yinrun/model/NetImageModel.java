package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "net_images")
public class NetImageModel implements Serializable
{
    //
    private static final long serialVersionUID = -2000762562227757052L;

    @Id
    @Column(name = "id")
    private Long              id;

    @Column(name = "url")
    private String            url;

    @Column(name = "type")
    private Integer           type;

    @Column(name = "name")
    private String            name;

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

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
