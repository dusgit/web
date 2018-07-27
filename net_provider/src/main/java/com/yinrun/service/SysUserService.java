package com.yinrun.service;

import java.util.List;

import com.yinrun.bean.LoginVo;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysUserModel;

public interface SysUserService extends BaseService<SysUserModel>
{
    /**
     * 验证登录
     * @param user
     * @return
     * @author 张亮亮
     */
    public ResultModule verifyLogin(SysUserModel user);

    /**
     * token是否有效
     * @param token
     * @return
     * @author 张亮亮
     */
    public LoginVo isLogin(String token);

    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<SysUserModel> findByExample(SysUserModel item, PageVo page);

    /**
     * 查询用户权限
     * @param id
     * @return
     * @author 张亮亮
     */
    public List<SysPowerModel> findPowerByUserId(Long id);

    /**
     * 新增用户基本信息和角色
     * @param item
     * @return
     * @author 张亮亮
     */
    public ResultModule saveUserInfoAndPower(SysUserModel item, String token);

    /**
     * 更新用户基本信息和角色
     * @param item
     * @return
     * @author 张亮亮
     */
    public ResultModule updateUserInfoAndPower(SysUserModel item, String token);

    /**
     * 根据token返回用户当前角色
     * @param token
     * @return
     * @author 张亮亮
     */
    public List<SysRoleModel> findRoleByToken(String token);

    /**
     * 逻辑删除用户 state 改为9
     * @param item
     * @return
     * @author 张亮亮
     */
    public ResultModule deleteUserLogic(SysUserModel item);

    /**
     * 根据用户id查询用户角色
     * @param id
     * @return
     * @author 张亮亮
     */
    public List<SysRoleModel> findRoleByUserId(Long id);

}
