package com.yinrun.service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.NetBannerModel;

public interface NetBannerService extends BaseService<NetBannerModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 罗熹林
     */
    public PageResult<NetBannerModel> findByExample(NetBannerModel item, PageVo page);
    
    /**
     * 根据条件查询数据返回前端页面
     * @param item
     * @param page
     * @return
     * @author 罗熹林
     */
    public PageResult<NetBannerModel> findByExampleForNet(NetBannerModel item, PageVo page);
    
    /**
     * 新增轮播图片
     * @param banner
     * @return
     * @author 罗熹林
     */
    public ResultModule saveBanner(NetBannerModel banner);
    
    /**
     * 更新轮播图片
     * @param banner
     * @return
     * @author 罗熹林
     */
    public ResultModule updateBanner(NetBannerModel banner);
    
    /**
     * 删除轮播图片
     * @param banner
     * @return
     * @author 罗熹林
     */
    public ResultModule deleteBanner(NetBannerModel banner);
    
}
