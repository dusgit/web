package com.yinrun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.dao.SysDictDao;
import com.yinrun.model.SysDictModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("sysDictService")
public class SysDictServiceImpl implements SysDictService
{
    @Autowired
    private SysDictDao sysDictDao;
    @Override
    public SysDictModel findById(Long id)
    {
        return sysDictDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(SysDictModel item)
    {
        return sysDictDao.insert(item);
    }

    @Override
    public Integer update(SysDictModel item)
    {
        SysDictModel sysDictModel = sysDictDao.selectByPrimaryKey(item.getId());
        if (sysDictModel != null)
        {
            if (StringUtil.isNotEmpty(item.getCode()) && !item.getCode().equals(sysDictModel.getCode()))
            {
                sysDictModel.setCode(item.getCode());
            }
            if (StringUtil.isNotEmpty(item.getContent()) && !item.getContent().equals(sysDictModel.getContent()))
            {
                sysDictModel.setContent(item.getContent());
            }
            if (StringUtil.isNotEmpty(item.getType()) && !item.getType().equals(sysDictModel.getType()))
            {
                sysDictModel.setType(item.getType());
            }
            sysDictModel.setUpdateTime(DateUtil.getCurrentTime());
            return sysDictDao.updateByPrimaryKeySelective(sysDictModel);
        }
        return 0;
    }

    @Override
    public Integer deleteById(Long id)
    {
        return sysDictDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<SysDictModel> findByExample(SysDictModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(SysDictModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getType()))
        {
            criteria.andCondition("type = ", item.getType());
        }
        if (StringUtil.isNotEmpty(item.getCode()))
        {
            criteria.andCondition("code = ", item.getCode());
        }
        if (StringUtil.isNotEmpty(item.getContent()))
        {
            criteria.andCondition("content = ", item.getContent());
        }
        List<SysDictModel> list = sysDictDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }
}
