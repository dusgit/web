package com.yinrun.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "net_activity")
public class NetActivityModel implements Serializable
{
    //
    private static final long          serialVersionUID = 6690171928556040824L;

    @Id
    @Column(name = "id")
    private Long                       id;

    @Column(name = "title")
    private String                     title;

    @Column(name = "sequence")
    private Integer                    sequence;

    @Column(name = "state")
    private Integer                    state;

    @Column(name = "position")
    private String                     position;

    @Column(name = "activity_time")
    private Integer                    activityTime;

    @Column(name = "create_time")
    private Integer                    createTime;

    @Column(name = "update_time")
    private Integer                    updateTime;

    @Transient
    private List<NetAttachImagesModel> imgList;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public Integer getActivityTime()
    {
        return activityTime;
    }

    public void setActivityTime(Integer activityTime)
    {
        this.activityTime = activityTime;
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

    public List<NetAttachImagesModel> getImgList()
    {
        return imgList;
    }

    public void setImgList(List<NetAttachImagesModel> imgList)
    {
        this.imgList = imgList;
    }
}
