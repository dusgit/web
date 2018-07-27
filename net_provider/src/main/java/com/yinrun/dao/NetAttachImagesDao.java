package com.yinrun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.NetAttachImagesModel;

public interface NetAttachImagesDao extends GenericMapper<NetAttachImagesModel>
{
    public List<NetAttachImagesModel> findByTargetIdAndType(@Param("targetId")Long targetId, @Param("type")String type);
    
    public Integer deleteByTargetIdAndType(@Param("targetId")Long targetId, @Param("type")String type);
}
