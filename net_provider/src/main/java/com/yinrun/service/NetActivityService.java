package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.NetActivityModel;

public interface NetActivityService extends BaseService<NetActivityModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 罗熹林
     */
    public PageResult<NetActivityModel> findByExample(NetActivityModel item, PageVo page);
    
    /**
     * 根据数据插入活动，并返回主键id
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule addActivity(NetActivityModel item);
    
    /**
     * 根据数据更新
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule updateActivity(NetActivityModel item);
    
    /**
     * 根据数据进行删除
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule deleteActivity(NetActivityModel item);
    
}
