package com.yinrun.bean;

import java.io.Serializable;
import java.util.Map;

public class ResultModule implements Serializable
{
    private static final long   serialVersionUID = 3218557879835881548L;

    private String              status;

    private Integer             code;

    private String              message;

    private Map<String, Object> results;

    public static ResultModule success(String message)
    {
        ResultModule module = new ResultModule();
        module.setStatus("success");
        module.setCode(200000);
        module.setMessage(message);
        return module;
    }

    public static ResultModule success(String message, Integer code)
    {
        ResultModule module = new ResultModule();
        module.setStatus("success");
        module.setCode(code);
        module.setMessage(message);
        return module;
    }

    public static ResultModule success(String message,
            Map<String, Object> results)
    {
        ResultModule module = new ResultModule();
        module.setStatus("success");
        module.setCode(200000);
        module.setMessage(message);
        module.setResults(results);
        return module;
    }

    public static ResultModule success(String message, Integer code,
            Map<String, Object> results)
    {
        ResultModule module = new ResultModule();
        module.setStatus("success");
        module.setCode(code);
        module.setMessage(message);
        module.setResults(results);
        return module;
    }

    public static ResultModule error(String message)
    {
        ResultModule module = new ResultModule();
        module.setStatus("false");
        module.setCode(500000);
        module.setMessage(message);
        return module;
    }

    public static ResultModule error(String message, Integer code)
    {
        ResultModule module = new ResultModule();
        module.setStatus("false");
        module.setCode(code);
        module.setMessage(message);
        return module;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Map<String, Object> getResults()
    {
        return results;
    }

    public void setResults(Map<String, Object> results)
    {
        this.results = results;
    }
}
