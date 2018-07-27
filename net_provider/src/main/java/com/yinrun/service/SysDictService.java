package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.model.SysDictModel;

public interface SysDictService extends BaseService<SysDictModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<SysDictModel> findByExample(SysDictModel item, PageVo page);
}
