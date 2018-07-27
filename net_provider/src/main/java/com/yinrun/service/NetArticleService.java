package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.NetArticleModel;

public interface NetArticleService extends BaseService<NetArticleModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<NetArticleModel> findByExample(NetArticleModel item, PageVo page);

    /**
     * 查询文章详情
     * @param id
     * @return
     * @author 张亮亮
     */
    public ResultModule findArticleDetail(Long id);
}
