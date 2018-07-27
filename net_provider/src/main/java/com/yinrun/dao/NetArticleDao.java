package com.yinrun.dao;

import org.apache.ibatis.annotations.Param;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.NetArticleModel;


public interface NetArticleDao extends GenericMapper<NetArticleModel>
{
    /**
     * 前一篇
     * @return
     * @author 张亮亮
     */
    public NetArticleModel findPreviousOne(@Param(value = "id") Long id);

    /**
     * 后一篇
     * @return
     * @author 张亮亮
     */
    public NetArticleModel findNextOne(@Param(value = "id") Long id);
}
