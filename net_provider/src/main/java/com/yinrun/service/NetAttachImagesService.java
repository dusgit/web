package com.yinrun.service;

import java.util.List;

import com.yinrun.model.NetAttachImagesModel;

public interface NetAttachImagesService extends BaseService<NetAttachImagesModel>
{
    /**
     * 根据targetId查询
     * @param targetId
     * @return
     * @author 罗熹林
     */
    public List<NetAttachImagesModel> list(Long targetId, String type);
}
