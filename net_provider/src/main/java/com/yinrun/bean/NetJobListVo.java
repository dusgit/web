package com.yinrun.bean;

import java.io.Serializable;
import java.util.List;

import com.yinrun.model.NetJobModel;

public class NetJobListVo implements Serializable
{
    //
    private static final long serialVersionUID = -2609098459569120863L;
    private String code;
    private String content;
    private List<NetJobModel> jobList;
    
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
    public List<NetJobModel> getJobList()
    {
        return jobList;
    }
    public void setJobList(List<NetJobModel> jobList)
    {
        this.jobList = jobList;
    }
    
}
