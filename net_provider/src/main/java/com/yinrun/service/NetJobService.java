package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.NetJobModel;

public interface NetJobService extends BaseService<NetJobModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<NetJobModel> findByExample(NetJobModel item, PageVo page);

    /**
     * 查询所有职位，并分类
     * @return
     * @author 张亮亮
     */
    public ResultModule findJobListForNet();
}
