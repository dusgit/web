package com.yinrun.utils;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;

/**
 * <p>File：PageHelperUtil.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2018 2018年2月7日 下午3:20:51</p>
 * <p>Company: </p>
 * @author 张亮亮
 * @version 1.0
 */
public class PageHelperUtil
{
    /**
     * @param page
     * @author 张亮亮
     */
    public static void startPage(PageVo page)
    {
        PageHelper.startPage(page.getCurrentPage(),
                page.getPageSize());
    }

    /**
     * 获取PageInfo对象
     * @param list 结果集
     * @return
     * @author 张亮亮
     */
    public static <T> PageInfo<T> getPageInfo(List<T> list)
    {
        PageInfo<T> page = new PageInfo<T>(list);
        return page;
    }

    /**
     * 获取总数
     * @param list 结果集
     * @return
     * @author 张亮亮
     */
    public static <T> long getTotal(List<T> list)
    {
        PageInfo<T> page = new PageInfo<T>(list);
        return page.getTotal();
    }

    /**
     * 获取完整Pagination对象
     * @param pagination 分页基本对象
     * @param list 分页插件查询结果集
     * @return
     * @author 张亮亮
     */
    public static <T> PageVo getPagination(PageVo pagination,
            List<T> list)
    {
        long totalCount = getTotal(list);
        pagination = PageUtils.getPagination(pagination, totalCount);
        return pagination;
    }

    /**
     * 获取封装的PaginateResult对象
     * @param list 分页插件查找后的集合
     * @return
     * @author 张亮亮
     */
    public static <T> PageResult<T> getPaginateResult(
            PageVo pagination, List<T> list)
    {
        pagination = getPagination(pagination, list);
        PageResult<T> result = new PageResult<>(pagination, list);
        return result;
    }
}
