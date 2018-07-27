package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "net_attach_images")
public class NetAttachImagesModel implements Serializable
{
    //
    private static final long serialVersionUID = -6429820939410478401L;

    @Id
    @Column(name = "id")
    private Long           id;

    @Column(name = "target_id")
    private Integer           targetId;                                // 指定对应的图片类型中的具体id

    @Column(name = "sequence")
    private Integer           sequence;

    @Column(name = "url")
    private String            url;

    @Column(name = "type")
    private String            type;                                    // 对应的图片类型

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

    public Integer getTargetId()
    {
        return targetId;
    }

    public void setTargetId(Integer targetId)
    {
        this.targetId = targetId;
    }

    public Integer getSequence()
    {
        return sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence = sequence;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
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
