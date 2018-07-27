package com.yinrun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinrun.bean.NetJobListVo;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.NetActivityDao;
import com.yinrun.dao.NetAttachImagesDao;
import com.yinrun.dao.NetJobDao;
import com.yinrun.dao.SysDictDao;
import com.yinrun.model.NetActivityModel;
import com.yinrun.model.NetAttachImagesModel;
import com.yinrun.model.NetJobModel;
import com.yinrun.model.SysDictModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("netJobService")
public class NetJobServiceImpl implements NetJobService
{
    @Autowired
    private NetJobDao netJobDao;

    @Autowired
    private SysDictDao sysDictDao;
    
    @Autowired
    private NetActivityDao netActivityDao;
    
    @Autowired
    private NetAttachImagesDao netAttachImagesDao;
    
    @Override
    public NetJobModel findById(Long id)
    {
        return netJobDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(NetJobModel item)
    {
        return netJobDao.insert(item);
    }

    @Override
    public Integer update(NetJobModel item)
    {
        NetJobModel netJobModel = netJobDao.selectByPrimaryKey(item.getId());
        if (netJobModel != null)
        {
            if (StringUtil.isNotEmpty(item.getPosition()) && !item.getPosition().equals(netJobModel.getPosition()))
            {
                netJobModel.setPosition(item.getPosition());
            }
            if (item.getSequence() != null && !item.getSequence().equals(netJobModel.getSequence()))
            {
                netJobModel.setSequence(item.getSequence());
            }
            if (item.getState() != null && !item.getState().equals(netJobModel.getState()))
            {
                netJobModel.setState(item.getState());
            }
            if (item.getType() != null && !item.getType().equals(netJobModel.getType()))
            {
                netJobModel.setType(item.getType());
            }
            netJobModel.setContent(item.getContent());
            netJobModel.setUpdateTime(DateUtil.getCurrentTime());
            return netJobDao.updateByPrimaryKeySelective(netJobModel);
        }
        return 0;
    }

    @Override
    public Integer deleteById(Long id)
    {
        return netJobDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<NetJobModel> findByExample(NetJobModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetJobModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getPosition()))
        {
            criteria.andCondition("position = ", item.getPosition());
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state = ", item.getState());
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type = ", item.getType());
        }
        example.setOrderByClause("sequence desc ,create_time desc");
        List<NetJobModel> list = netJobDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule findJobListForNet()
    {
        Map<String, Object> results = new HashMap<String, Object>();
        // 获取分类
        Example example = new Example(SysDictModel.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("type = 'job'");
        List<SysDictModel> jobDict = sysDictDao.selectByExample(example);
        Example jobExample = new Example(NetJobModel.class);
        Criteria jobCriteria = jobExample.createCriteria();
        jobCriteria.andCondition("state = 1");
        jobExample.setOrderByClause(" sequence desc , create_time desc");
        List<NetJobModel> jobList = netJobDao.selectByExample(jobExample);
        List<NetJobListVo> jobVoList = new ArrayList<NetJobListVo>();
        for (SysDictModel dict : jobDict)
        {
            NetJobListVo jobVo = new NetJobListVo();
            jobVo.setCode(dict.getCode());
            jobVo.setContent(dict.getContent());
            jobVo.setJobList(new ArrayList<NetJobModel>());
            jobVoList.add(jobVo);
        }
        // 获取分类内容
        for (NetJobModel job : jobList)
        {
            for (NetJobListVo jobVo : jobVoList)
            {
                if (job.getType() != null && job.getType().toString().equals(jobVo.getCode()))
                {
                    if (job.getState() == 1)
                    {
                        jobVo.getJobList().add(job);
                    }
                }
            }
        }
        results.put("jobList", jobVoList);
        // 员工活动
        Example activityExample = new Example(NetActivityModel.class);
        Criteria activityCriteria = activityExample.createCriteria();
        activityCriteria.andCondition("state =", 1);
        activityExample.setOrderByClause("sequence desc");
        List<NetActivityModel> activityList = netActivityDao.selectByExample(activityExample);
        for(NetActivityModel activityModel : activityList)
        {
            Long targetId = activityModel.getId();
            List<NetAttachImagesModel> attachImagesList = netAttachImagesDao.findByTargetIdAndType(targetId, "activity");
            activityModel.setImgList(attachImagesList);
        }
        results.put("activityList", activityList);
        return ResultModule.success("查询成功", results);
    }
}
