package com.yinrun.bean;

import java.io.Serializable;
import java.util.List;

public class PowerVo implements Serializable
{
    //
    private static final long serialVersionUID = -5032142962550036313L;

    private String            name;

    private String            power;

    private String            link;

    private String            code;

    private String            ico;

    private List<PowerVo>     subList;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPower()
    {
        return power;
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public List<PowerVo> getSubList()
    {
        return subList;
    }

    public void setSubList(List<PowerVo> subList)
    {
        this.subList = subList;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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
