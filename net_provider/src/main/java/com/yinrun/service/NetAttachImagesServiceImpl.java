package com.yinrun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinrun.dao.NetAttachImagesDao;
import com.yinrun.model.NetAttachImagesModel;

@Service("netAttachImagesService")
public class NetAttachImagesServiceImpl implements NetAttachImagesService
{

    @Autowired
    private NetAttachImagesDao netAttachImagesDao;
    
    @Override
    public NetAttachImagesModel findById(Long id)
    {
        return null;
    }

    @Override
    public Integer insert(NetAttachImagesModel item)
    {
        return null;
    }

    @Override
    public Integer update(NetAttachImagesModel item)
    {
        return null;
    }

    @Override
    public Integer deleteById(Long id)
    {
        return null;
    }

    @Override
    public List<NetAttachImagesModel> list(Long targetId, String type)
    {
        return netAttachImagesDao.findByTargetIdAndType(targetId, type);
    }
}
