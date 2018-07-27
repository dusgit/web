package com.yinrun.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.dao.SysPowerDao;
import com.yinrun.model.SysPowerModel;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("sysPowerService")
public class SysPowerServiceImpl implements SysPowerService
{

    @Autowired
    private SysPowerDao                                  sysPowerDao;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Map<String, Object>> redis;

    @Override
    public SysPowerModel findById(Long id)
    {
        return sysPowerDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(SysPowerModel item)
    {
        return sysPowerDao.insert(item);
    }

    @Override
    public Integer update(SysPowerModel item)
    {
        return sysPowerDao.updateByPrimaryKey(item);
    }

    @Override
    public Integer deleteById(Long id)
    {
        return sysPowerDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<SysPowerModel> findByExample(SysPowerModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(SysPowerModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getName()))
        {
            criteria.andCondition("name = ", item.getName());
        }
        if (StringUtil.isNotEmpty(item.getCode()))
        {
            criteria.andCondition("code = ", item.getCode());
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type = ", item.getType());
        }
        if (StringUtil.isNotEmpty(item.getPower()))
        {
            criteria.andCondition("power = ", item.getPower());
        }
        List<SysPowerModel> list = sysPowerDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }
}
