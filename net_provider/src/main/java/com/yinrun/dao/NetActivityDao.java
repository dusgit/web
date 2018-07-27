package com.yinrun.dao;

import com.yinrun.interfaces.GenericMapper;
import com.yinrun.model.NetActivityModel;

public interface NetActivityDao extends GenericMapper<NetActivityModel>
{
    public Long insertActivity(NetActivityModel activityModel);
}
