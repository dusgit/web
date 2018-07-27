package com.yinrun.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_dict")
public class SysDictModel implements Serializable
{
    private static final long serialVersionUID = 6281331202299013076L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String            code;                                   // 编码

    @Column(name = "content")
    private String            content;                                // 内容

    @Column(name = "type")
    private String            type;                                   // 类型


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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
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
