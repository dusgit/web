package com.yinrun.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "net_banner")
public class NetBannerModel implements Serializable
{
    //
    private static final long serialVersionUID = -3936901631935166864L;

    @Id
    @Column(name = "id")
    private Long              id;

    @Column(name = "img_url")
    private String            imgUrl;

    @Column(name = "link")
    private String            link;

    @Column(name = "begin_time")
    private Integer           beginTime;

    @Column(name = "end_time")
    private Integer           endTime;

    @Column(name = "type") // 图片类型:1-首页，2-内页
    private Integer           type;

    @Column(name = "img_name")
    private String            imgName;

    @Column(name = "sequence") // 指定排序，数字越大越置顶
    private Integer           sequence;

    @Column(name = "state") // 类别状态:1-正常,0-禁用
    private Integer           state;

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

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Integer getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime)
    {
        this.beginTime = beginTime;
    }

    public Integer getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Integer endTime)
    {
        this.endTime = endTime;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getImgName()
    {
        return imgName;
    }

    public void setImgName(String imgName)
    {
        this.imgName = imgName;
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
