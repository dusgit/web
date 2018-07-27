package com.yinrun.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>File：PageResult.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年2月7日 下午3:20:02</p>
 * <p>Company: </p>
 * @author 张亮亮
 * @version 1.0
 */
public class PageResult<T> implements Serializable
{


    private static final long serialVersionUID = -2549791837383002936L;

    public PageResult()
    {
        super();
    }

    public PageResult(PageVo page, List<T> list)
    {
        this.page = page;
        this.list = list;
    }

    private PageVo  page;

    private List<T>    list;

    public PageVo getPage()
    {
        return page;
    }

    public void setPage(PageVo page)
    {
        this.page = page;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }

    public boolean hasContents()
    {
        return list == null ? false : !this.list.isEmpty();
    }
}