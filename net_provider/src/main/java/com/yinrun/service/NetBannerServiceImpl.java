package com.yinrun.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.NetBannerDao;
import com.yinrun.model.NetBannerModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("netBannerService")
public class NetBannerServiceImpl implements NetBannerService
{
    @Autowired
    private NetBannerDao netBannerDao;

    @Override
    public NetBannerModel findById(Long id)
    {
        return netBannerDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(NetBannerModel item)
    {
        return netBannerDao.insert(item);
    }

    @Override
    public Integer update(NetBannerModel item)
    {
        return netBannerDao.updateByPrimaryKey(item);
    }

    @Override
    public Integer deleteById(Long id)
    {
        return netBannerDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<NetBannerModel> findByExample(NetBannerModel item,
            PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetBannerModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getImgName()))
        {
            criteria.andCondition("img_name =", item.getImgName());
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type =", item.getType());
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state =", item.getState());
        }
        criteria.andCondition("state < 9 ");
        PageHelper.orderBy("sequence desc");
        List<NetBannerModel> list = netBannerDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule saveBanner(NetBannerModel item)
    {
        item.setCreateTime(DateUtil.getCurrentTime());
        item.setUpdateTime(DateUtil.getCurrentTime());
        if (netBannerDao.insert(item) > 0)
        {
            return ResultModule.success("保存成功");
        }
        return ResultModule.error("保存失败");
    }

    @Override
    public ResultModule updateBanner(NetBannerModel banner)
    {
        NetBannerModel bannerModel = netBannerDao.selectByPrimaryKey(banner.getId());
        if (bannerModel == null)
        {
            return ResultModule.error("该轮播图已不存在");
        }
        //判断图片地址是否变化
        if (banner.getImgUrl() != bannerModel.getImgUrl())
        {
            bannerModel.setImgUrl(banner.getImgUrl());
        }
        //判断图片外链是否变化
        if (banner.getLink() != bannerModel.getLink())
        {
            bannerModel.setLink(banner.getLink());
        }
        //判断图片开始轮播时间是否变化
        if (banner.getBeginTime() != bannerModel.getBeginTime())
        {
            bannerModel.setBeginTime(banner.getBeginTime());
        }
        //判断图片结束轮播时间是否变化
        if (banner.getEndTime() != bannerModel.getEndTime())
        {
            bannerModel.setEndTime(banner.getEndTime());
        }
        //判断图片轮播类型是否变化
        /*if (banner.getType() != bannerModel.getType())
        {
            bannerModel.setType(banner.getType());
        }*/
        //判断图片置顶排序是否变化
        if (banner.getSequence() != bannerModel.getSequence())
        {
            bannerModel.setSequence(banner.getSequence());
        }
        //判断图片状态是否变化
        if (banner.getState() != bannerModel.getState())
        {
            bannerModel.setState(banner.getState());
        }
        bannerModel.setUpdateTime(DateUtil.getCurrentTime());
        Integer res = netBannerDao.updateByPrimaryKey(bannerModel);
        if (res > 0)
        {
            return ResultModule.success("更新轮播图片成功");
        }
        return ResultModule.error("更新轮播图片失败");
    }

    @Override
    public ResultModule deleteBanner(NetBannerModel banner)
    {
        Integer res = netBannerDao.deleteByPrimaryKey(banner.getId());
        if (res > 0)
        {
            return ResultModule.success("删除轮播图片成功");
        }
        return ResultModule.error("删除轮播图片失败");
    }

    @Override
    public PageResult<NetBannerModel> findByExampleForNet(NetBannerModel item,
            PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetBannerModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getImgName()))
        {
            criteria.andCondition("img_name =", item.getImgName());
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type =", item.getType());
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state =", item.getState());
        }
        PageHelper.orderBy("sequence desc");
        List<NetBannerModel> list = netBannerDao.selectByExample(example);
        List<NetBannerModel> resultList = new ArrayList<>();
        Integer nowTime = DateUtil.getCurrentTime();
        for(NetBannerModel bannerModel : list) {
            if (bannerModel.getBeginTime() != null && bannerModel.getEndTime() != null)
            {
                if (bannerModel.getBeginTime() < nowTime && nowTime < bannerModel.getEndTime())
                {
                    resultList.add(bannerModel);
                }
                continue;
            }
            if (bannerModel.getBeginTime() != null && bannerModel.getEndTime() == null)
            {
                if (bannerModel.getBeginTime() < nowTime)
                {
                    resultList.add(bannerModel);
                }
                continue;
            }
            if (bannerModel.getBeginTime() == null && bannerModel.getEndTime() != null)
            {
                if (nowTime < bannerModel.getEndTime())
                {
                    resultList.add(bannerModel);
                }
                continue;
            }
            resultList.add(bannerModel);
        }
        return PageHelperUtil.getPaginateResult(page, resultList);
    }
}































