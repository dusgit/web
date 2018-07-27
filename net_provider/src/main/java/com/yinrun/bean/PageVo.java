package com.yinrun.bean;

import java.io.Serializable;

/**
 * <p>File：PageVo.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年2月7日 下午3:16:55</p>
 * <p>Company: </p>
 * @author 张亮亮
 * @version 1.0
 */
public class PageVo implements Serializable
{

    //
    private static final long serialVersionUID = 3497474722244158035L;

    /**
     * 构造器一
     */
    public PageVo()
    {
        super();
    }

    /**
     * 构造器二
     */
    public PageVo(int currentPage, int pageSize)
    {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * 构造器三
     */
    public PageVo(boolean hasPreviousPage, boolean hasNextPage,
            int pageSize, int totalPage, int currentPage, int startIndex,
            long totalCount)
    {
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.startIndex = startIndex;
        this.totalCount = totalCount;
    }

    // 是否有上一页
    private boolean hasPreviousPage;

    // 是否有下一页
    private boolean hasNextPage;

    // 每页的记录数
    private int     pageSize    = 10;

    // 当前是第几页
    private int     currentPage = 1;

    // 记录开始位置
    private int     startIndex;

    // 记录的总数量
    private long    totalCount;

    // 记录的总页数
    private int     totalPage;

    /**
     * 取得是否有上页的标记
     * @return boolean 是否有上页的标记(true：有,false：无)
     */
    public boolean getHasPreviousPage()
    {
        return hasPreviousPage;
    }

    /**
     * 设置是否有上页的标记
     * @param hasPreviousPage 是否有上页的标记(true：有,false：无)
     */
    public void setHasPreviousPage(boolean hasPreviousPage)
    {
        this.hasPreviousPage = hasPreviousPage;
    }

    /**
     * 取得是否有下页的标记
     * @return boolean 是否有下页的标记(true：有,false：无)
     */
    public boolean getHasNextPage()
    {
        return hasNextPage;
    }

    /**
     * 设置是否有下页的标记
     * @param hasNextPage 是否有下页的标记(true：有,false：无)
     */
    public void setHasNextPage(boolean hasNextPage)
    {
        this.hasNextPage = hasNextPage;
    }

    /**
     * 取得每页显示的资料笔数
     * @return int 每页显示的资料笔数(默认为20)
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * 设置每页显示的资料笔数
     * @param pageSize 每页显示的资料笔数(默认为20)
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * 取得当前显示的页标
     * @return int 当前显示的页标
     */
    public int getCurrentPage()
    {
        return currentPage;
    }

    /**
     * 设置当前显示的页标
     * @param currentPage 当前显示的页标
     */
    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    /**
     * 取得记录开始位置
     * @return int 记录开始位置
     */
    public int getStartIndex()
    {
        return startIndex;
    }

    /**
     * 设置记录开始位置
     * @param startIndex 记录开始位置
     */
    public void setStartIndex(int startIndex)
    {
        this.startIndex = startIndex;
    }

    /**
     * 取得资料总笔数
     * @return int 资料总笔数
     */
    public long getTotalCount()
    {
        return totalCount;
    }

    /**
     * 设置资料总笔数
     * @param totalCount 资料总笔数
     */
    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * 取得资料总页数
     * @return int 资料总页数
     */
    public int getTotalPage()
    {
        return totalPage;
    }

    /**
     * 设置资料总页数
     * @param totalPage 资料总页数
     */
    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }
}
