package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.NetImageModel;

public interface NetImageService extends BaseService<NetImageModel>
{
    
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 罗熹林
     */
    public PageResult<NetImageModel> findByExample(NetImageModel item, PageVo page);
    
    /**
     * 根据条件插入数据
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule saveImage(NetImageModel item);
    
    /**
     * 根据数据修改
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule updateImage(NetImageModel item);
    
    /**
     * 根据id删除
     * @param item
     * @return
     * @author 罗熹林
     */
    public ResultModule deleteImage(NetImageModel item);
}
