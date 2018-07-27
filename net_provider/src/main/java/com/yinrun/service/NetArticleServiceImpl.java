package com.yinrun.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.NetArticleDao;
import com.yinrun.model.NetArticleModel;
import com.yinrun.model.NetJobModel;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("articleService")
public class NetArticleServiceImpl implements NetArticleService
{
    @Autowired
    private NetArticleDao netArticleDao;

    @Override
    public NetArticleModel findById(Long id)
    {
        return netArticleDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(NetArticleModel item)
    {
        return netArticleDao.insert(item);
    }

    @Override
    public Integer update(NetArticleModel item)
    {
        return netArticleDao.updateByPrimaryKey(item);
    }

    @Override
    public Integer deleteById(Long id)
    {
        return netArticleDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<NetArticleModel> findByExample(NetArticleModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(NetArticleModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getTitle()))
        {
            criteria.andCondition("title = ", item.getTitle());
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state = ", item.getState());
        }
        if (item.getType() != null)
        {
            criteria.andCondition("type = ", item.getType());
        }
        criteria.andCondition("valid_flag = 1");
        example.setOrderByClause(" sequence desc , create_time desc");

        System.out.println(example.getOrderByClause());
        List<NetArticleModel> list = netArticleDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule findArticleDetail(Long id)
    {
        NetArticleModel articleParam = new NetArticleModel();
        articleParam.setId(id);
        articleParam.setState(1);
        articleParam.setValidFlag(1);
        Example example = new Example(NetJobModel.class);
        Criteria criteria = example.createCriteria();
        criteria.andCondition("id = ", id);
        criteria.andCondition("state = 1");
        criteria.andCondition("valid_flag = 1");
        List<NetArticleModel> articleList = netArticleDao.selectByExample(example);
        if (articleList != null && articleList.size() > 0)
        {
            Map<String, Object> results = new HashMap<String, Object>();
            results.put("article", articleList.get(0));
            // 查询下一篇
            NetArticleModel previousArticle = netArticleDao.findPreviousOne(id);
            if (previousArticle != null)
            {
                previousArticle.setContent("");
            }
            // 查询上一篇
            NetArticleModel nextArticle = netArticleDao.findNextOne(id);
            if (nextArticle != null)
            {
                nextArticle.setContent("");
            }
            results.put("previous", previousArticle);
            results.put("next", nextArticle);
            return ResultModule.success("查询成功", results);
        }
        else
        {
            return ResultModule.error("文章不存在");
        }
    }
}
