package com.yinrun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.NetImageDao;
import com.yinrun.model.NetImageModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import aj.org.objectweb.asm.Type;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("betImageService")
public class NetImageServiceImpl implements NetImageService
{

    @Autowired
    private NetImageDao netImageDao;
    
    @Override
    public NetImageModel findById(Long id)
    {
        return null;
    }

    @Override
    public Integer insert(NetImageModel item)
    {
        return null;
    }

    @Override
    public Integer update(NetImageModel item)
    {
        return null;
    }

    @Override
    public Integer deleteById(Long id)
    {
        return null;
    }

    @Override
    public PageResult<NetImageModel> findByExample(NetImageModel item,
            PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetImageModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getName()))
        {
            criteria.andCondition("name like", ("%" + item.getName() + "%"));
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type =", item.getType());
        }
        PageHelper.orderBy("create_time desc");
        List<NetImageModel> list = netImageDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule saveImage(NetImageModel item)
    {
        item.setCreateTime(DateUtil.getCurrentTime());
        item.setUpdateTime(DateUtil.getCurrentTime());
        if (netImageDao.insert(item) > 0)
        {
            return ResultModule.success("保存成功");
        }
        return ResultModule.error("保存失败");
    }

    @Override
    public ResultModule updateImage(NetImageModel item)
    {
        NetImageModel imageModel = netImageDao.selectByPrimaryKey(item.getId());
        if (imageModel == null)
        {
            return ResultModule.error("该图片已不存在");
        }
        if (item.getUrl() != imageModel.getUrl())
        {
            imageModel.setUrl(item.getUrl());
        }
        if (item.getName() != imageModel.getName())
        {
            imageModel.setName(item.getName());
        }
        if (item.getType() != imageModel.getType())
        {
            imageModel.setType(item.getType());
        }
        imageModel.setUpdateTime(DateUtil.getCurrentTime());
        Integer res = netImageDao.updateByPrimaryKey(imageModel);
        if (res > 0)
        {
            return ResultModule.success("更新图片成功");
        }
        return ResultModule.error("更新图片失败");
    }

    @Override
    public ResultModule deleteImage(NetImageModel item)
    {
        int res = netImageDao.deleteByPrimaryKey(item.getId());
        if (res > 0)
        {
            return ResultModule.success("删除图片成功");
        }
        return ResultModule.error("删除图片失败");
    }
}




































