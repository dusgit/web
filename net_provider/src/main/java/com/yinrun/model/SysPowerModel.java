package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_power")
public class SysPowerModel implements Serializable
{
    private static final long serialVersionUID = 6281331202299013076L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String            name;                                   // 权限名称

    @Column(name = "code")
    private String            code;                                   // 权限编码

    @Column(name = "parent_code")
    private String            parentCode;                             // 上级编码

    @Column(name = "type")
    private Integer           type;                                   // 权限类型1菜单，2按钮，3资源

    @Column(name = "power")
    private String            power;                                  // 资源路径

    @Column(name = "link")
    private String            link;                                   // 跳转地址

    @Column(name = "mark")
    private String            mark;                                   // 权限备注

    @Column(name = "ico")
    private String            ico;                                    // 图标

    @Column(name = "sequence")
    private Integer           sequence;                               // 排序

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getPower()
    {
        return power;
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public Integer getSequence()
    {
        return sequence;
    }

    public void setSequence(Integer sequence)
    {
        this.sequence = sequence;
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

    public String getIco()
    {
        return ico;
    }

    public void setIco(String ico)
    {
        this.ico = ico;
    }
}
