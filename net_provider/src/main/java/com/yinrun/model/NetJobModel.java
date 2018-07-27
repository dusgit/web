package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "net_job")
public class NetJobModel implements Serializable
{
    //
    private static final long serialVersionUID = 6264458219093960966L;

    @Id
    @Column(name = "id")
    private Long              id;

    @Column(name = "position")
    private String            position;                               // 职位名称

    @Column(name = "type")
    private Integer           type;                                   // 职位分类

    @Column(name = "sequence")
    private Integer           sequence;

    @Column(name = "state")
    private Integer           state;                                  // 职位状态，0-禁用，1-可用

    @Column(name = "content")
    private String            content;

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

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getSequence()
    {
        return sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence = sequence;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
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
