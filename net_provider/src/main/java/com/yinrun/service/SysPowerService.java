package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.model.SysPowerModel;

public interface SysPowerService extends BaseService<SysPowerModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<SysPowerModel> findByExample(SysPowerModel item, PageVo page);


}
