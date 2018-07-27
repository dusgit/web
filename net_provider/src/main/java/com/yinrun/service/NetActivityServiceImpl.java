package com.yinrun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.NetActivityDao;
import com.yinrun.dao.NetAttachImagesDao;
import com.yinrun.model.NetActivityModel;
import com.yinrun.model.NetAttachImagesModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("netActivityService")
public class NetActivityServiceImpl implements NetActivityService
{

    @Autowired
    private NetActivityDao netActivityDao;
    
    @Autowired
    private NetAttachImagesDao netAttachImagesDao;
    
    @Override
    public NetActivityModel findById(Long id)
    {
        return null;
    }

    @Override
    public Integer insert(NetActivityModel item)
    {
        return null;
    }

    @Override
    public Integer update(NetActivityModel item)
    {
        return null;
    }

    @Override
    public Integer deleteById(Long id)
    {
        return null;
    }

    @Override
    public PageResult<NetActivityModel> findByExample(NetActivityModel item,
            PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetActivityModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getTitle()))
        {
            criteria.andCondition("title like", ("%" + item.getTitle() + "%"));
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state =", item.getState());
        }
        PageHelper.orderBy("sequence desc");
        List<NetActivityModel> list = netActivityDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule addActivity(NetActivityModel item)
    {
        item.setCreateTime(DateUtil.getCurrentTime());
        item.setUpdateTime(DateUtil.getCurrentTime());
        Long insertAmount = netActivityDao.insertActivity(item);
        int insertCount = 0;
        if (insertAmount > 0)
        {
            //根据id，开始插入相关图片
            for(NetAttachImagesModel model : item.getImgList())
            {
                NetAttachImagesModel ImagesModel = new NetAttachImagesModel();
                ImagesModel.setTargetId(item.getId().intValue());
                ImagesModel.setSequence(model.getSequence());
                ImagesModel.setType("activity");
                ImagesModel.setUrl(model.getUrl());
                ImagesModel.setCreateTime(DateUtil.getCurrentTime());
                ImagesModel.setUpdateTime(DateUtil.getCurrentTime());
                if (netAttachImagesDao.insert(ImagesModel) > 0)
                {
                    insertCount++;
                }
            }
            if (insertCount != 6)
            {
                return ResultModule.error("保存图片失败");
            }
            return ResultModule.success("保存员工活动成功");
        }        
        return ResultModule.success("保存员工活动失败");
    }

    @Override
    public ResultModule updateActivity(NetActivityModel item)
    {
        NetActivityModel activityModel = netActivityDao.selectByPrimaryKey(item.getId());
        if (activityModel == null)
        {
            return ResultModule.error("该员工活动已不存在");
        }
        // 判断标题是否变化
        if (item.getTitle() != activityModel.getTitle())
        {
            activityModel.setTitle(item.getTitle());
        }
        // 判断置顶排序是否变化
        if (item.getSequence() != activityModel.getSequence())
        {
            activityModel.setSequence(item.getSequence());
        }
        // 判断状态是否变化
        if (item.getState() != activityModel.getState())
        {
            activityModel.setState(item.getState());
        }
        activityModel.setUpdateTime(DateUtil.getCurrentTime());
        int insertCount = 0;
        if (netActivityDao.updateByPrimaryKey(activityModel) > 0)
        {
            List<NetAttachImagesModel> imgList = item.getImgList();
            for (NetAttachImagesModel model : imgList)
            {
                NetAttachImagesModel attachImagesModel = netAttachImagesDao.selectByPrimaryKey(model.getId());
                //判断url是否变化
                if (attachImagesModel.getUrl() != model.getUrl())
                {
                    attachImagesModel.setUrl(model.getUrl());
                }
                //判断sequence是否变化
                if (attachImagesModel.getSequence() != model.getSequence())
                {
                    attachImagesModel.setSequence(model.getSequence());
                }
                attachImagesModel.setUpdateTime(DateUtil.getCurrentTime());
                if(netAttachImagesDao.updateByPrimaryKey(attachImagesModel)> 0)
                {
                    insertCount++;
                }
            }
            if (insertCount != 6)
            {
                return ResultModule.error("更新图片失败");
            }
            return ResultModule.success("更新员工活动成功");
        }
        return ResultModule.error("更新失败");
    }

    @Override
    public ResultModule deleteActivity(NetActivityModel item)
    {
        int res = netAttachImagesDao.deleteByTargetIdAndType(item.getId(), "activity");
        if (res >= 6)
        {
            if (netActivityDao.deleteByPrimaryKey(item.getId()) > 0)
            {
                return ResultModule.success("删除员工活动成功");
            }else {
                return ResultModule.error("仅删除图片信息请稍后再试");
            }
        }else if(res < 6){
            return ResultModule.error("删除部分图片失败");
        }
        return ResultModule.error("删除员工活动失败");
    }

}




















































