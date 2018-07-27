package com.yinrun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yinrun.bean.LoginVo;
import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.PowerVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.SysUserDao;
import com.yinrun.dao.SysUserRoleDao;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysUserModel;
import com.yinrun.model.SysUserRoleModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.MD5;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService
{
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao                               sysUserRoleDao;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Map<String, Object>> redis;

    @Override
    public SysUserModel findById(Long id)
    {
        return sysUserDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(SysUserModel nam)
    {
        return sysUserDao.insert(nam);
    }

    @Override
    public Integer update(SysUserModel nam)
    {
        return sysUserDao.updateByPrimaryKey(nam);
    }

    @Override
    public Integer deleteById(Long id)
    {
        return sysUserDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<SysUserModel> findByExample(SysUserModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(SysUserModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getUserName()))
        {
            criteria.andCondition("user_name = ", item.getUserName());
        }
        if (StringUtil.isNotEmpty(item.getRealName()))
        {
            criteria.andCondition("real_name = ", item.getRealName());
        }
        if (item.getState() != null)
        {
            criteria.andCondition("state = ", item.getState());
        }
        criteria.andCondition("state < 9 ");
        List<SysUserModel> list = sysUserDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }


    @Override
    public ResultModule verifyLogin(SysUserModel user)
    {
        SysUserModel userParam = new SysUserModel();
        userParam.setUserName(user.getUserName());
        userParam.setState(1);
        
        SysUserModel sysUserModel = sysUserDao.findOneByEntity(userParam);
        if (sysUserModel != null)
        {
            String userPassMd5 = MD5.encodePassword(user.getUserPass() + sysUserModel.getSalt());
            if (userPassMd5.equals(sysUserModel.getUserPass()))
            {
                String token = StringUtil.getToken();
                LoginVo loginVo = new LoginVo();
                loginVo.setId(sysUserModel.getId());
                loginVo.setUserName(sysUserModel.getUserName());
                // 查询用户权限
                List<SysPowerModel> powerList = sysUserDao.findPowerByUserId(sysUserModel.getId());
                Map<String, String> powerMap = new HashMap<String, String>();// getPowerVoList(powerList);
                List<PowerVo> menuList = getPowerVoList(powerList, powerMap);
                Map<String, Object> results = new HashMap<String, Object>();
                List<SysRoleModel> roleList = sysUserDao.findRoleByUserId(sysUserModel.getId());
                results.put("token", token);
                results.put("userInfo", loginVo);
                results.put("powerMap", powerMap);
                results.put("menuList", menuList);
                results.put("roleList", roleList);
                redis.set(token, results, 60 * 30, TimeUnit.SECONDS);// token有效期30分钟
                return ResultModule.success("登录成功", results);
            }
            else
            {
                return ResultModule.error("用户密码错误");
            }
        }
        else
        {
            return ResultModule.error("用户不存在");
        }
    }

    @Override
    public LoginVo isLogin(String token)
    {
        Map<String, Object> result = redis.get(token);
        return (LoginVo) result.get("userInfo");
    }

    @Override
    public List<SysPowerModel> findPowerByUserId(Long id)
    {
        return sysUserDao.findPowerByUserId(id);
    }

    private List<PowerVo> getPowerVoList(List<SysPowerModel> list, Map<String, String> powerMap)
    {
        Map<String, PowerVo> menuMap = new HashMap<String, PowerVo>();
        List<PowerVo> powerVoList = new ArrayList<PowerVo>();
        // 找出全部父级菜单code
        Set<String> menuCodeSet = new HashSet<String>();// 父级菜单集合
        for (SysPowerModel spm : list)
        {
            powerMap.put(spm.getPower(), spm.getLink());
            if (StringUtil.isNotEmpty(spm.getParentCode()) && !menuCodeSet.contains(spm.getParentCode()) && spm.getType() == 1)
            {
                menuCodeSet.add(spm.getParentCode());
            }
        }
        // 找出一级主菜单
        for (SysPowerModel spm : list)
        {
            if (menuCodeSet.contains(spm.getCode()) && !menuMap.containsKey(spm.getCode()))
            {
                PowerVo powerVo = new PowerVo();
                powerVo.setName(spm.getName());
                powerVo.setLink(spm.getLink());
                powerVo.setPower(spm.getPower());
                powerVo.setCode(spm.getCode());
                powerVo.setIco(spm.getIco());
                powerVo.setSubList(new ArrayList<PowerVo>());
                menuMap.put(spm.getCode(), powerVo);
                if (StringUtil.isEmpty(spm.getParentCode()))
                {
                    powerVoList.add(powerVo);
                }
            }
        }
        // 过滤二级菜单
        for (SysPowerModel spm : list)
        {
            if (StringUtil.isNotEmpty(spm.getParentCode()))
            {
                PowerVo powerVo = null;
                if (menuMap.get(spm.getCode()) != null)
                {
                    powerVo = menuMap.get(spm.getCode());
                }
                else
                {
                    powerVo = new PowerVo();
                    powerVo.setName(spm.getName());
                    powerVo.setLink(spm.getLink());
                    powerVo.setPower(spm.getPower());
                    powerVo.setCode(spm.getCode());
                    powerVo.setIco(spm.getIco());
                    powerVo.setSubList(null);
                }
                PowerVo parentPower = menuMap.get(spm.getParentCode());
                if (parentPower != null)
                {
                    if (spm.getType() == 1)
                    {
                        // 判断是否已经存在
                        boolean exist = false;
                        for (PowerVo pv : parentPower.getSubList())
                        {
                            if (pv.getCode().equals(powerVo.getCode()))
                            {
                                exist = true;
                            }
                        }
                        if (!exist)
                        {
                            parentPower.getSubList().add(powerVo);
                        }
                    }
                }
            }
        }
        return powerVoList;
    }

    @Override
    public ResultModule updateUserInfoAndPower(SysUserModel user, String token)
    {
        SysUserModel sysUserModel = sysUserDao.selectByPrimaryKey(user.getId());
        if (sysUserModel == null)
        {
            return ResultModule.error("用户不存在");
        }
        // 判断状态是否变化
        if (user.getState() != sysUserModel.getState())
        {
            sysUserModel.setState(user.getState());
        }
        // 判断真实姓名是否变化
        if (user.getRealName() != null && !user.getRealName().equals(sysUserModel.getRealName()))
        {
            sysUserModel.setRealName(user.getRealName());
        }
        // 判断头像地址是否变化
        if (StringUtil.isNotEmpty(user.getHeadUrl()) && (!user.getHeadUrl().equals(sysUserModel.getHeadUrl())))
        {
            sysUserModel.setHeadUrl(user.getHeadUrl());
        }
        // 判断是否重置密码
        if (StringUtil.isNotEmpty(user.getUserPass()))
        {
            sysUserModel.setUserPass(MD5.encodePassword(user.getUserPass() + sysUserModel.getSalt()));
        }
        // 设置更新时间
        sysUserModel.setUpdateTime(DateUtil.getCurrentTime());

        Integer res = update(sysUserModel);
        List<SysRoleModel> loginRoleList = findRoleByToken(token);
        List<SysRoleModel> roleList = sysUserDao.findRoleByUserId(user.getId());
        String[] roleIds = null;
        if (StringUtil.isNotEmpty(user.getRoleIds()))
        {
            roleIds = user.getRoleIds().split(",");
            for (String roleId : roleIds)
            {
                boolean existRole = false;
                for (SysRoleModel sysRoleModel : roleList)
                {
                    if (sysRoleModel.getId() == Long.valueOf(roleId))
                    {
                        existRole = true;
                    }
                }
                if (!existRole)
                {
                    // 新增角色关联关系
                    if (containsRole(loginRoleList, Long.valueOf(roleId)))
                    {
                        SysUserRoleModel sur = new SysUserRoleModel();
                        sur.setRoleId(Long.valueOf(roleId));
                        sur.setUserId(user.getId());
                        sur.setCreateTime(DateUtil.getCurrentTime());
                        sur.setUpdateTime(DateUtil.getCurrentTime());
                        Integer ex = sysUserRoleDao.insert(sur);
                        System.out.println(ex);
                    }
                }
            }
            for (SysRoleModel sysRoleModel : roleList)
            {
                boolean deleteRole = true;
                for (String roleId : roleIds)
                {
                    if (sysRoleModel.getId().equals(Long.valueOf(roleId)))
                    {
                        deleteRole = false;
                    }
                }
                if (deleteRole)
                {
                    // 删除角色关联关系
                    sysUserRoleDao.deleteByUserIdAndRoleId(user.getId(), sysRoleModel.getId());
                }
            }
        }
        if (res > 0)
        {
            return ResultModule.success("更新成功");
        }
        else
        {
            return ResultModule.success("更新失败");
        }
    }

    @Override
    public List<SysRoleModel> findRoleByToken(String token)
    {
        Map<String, Object> results = redis.get(token);
        return (List<SysRoleModel>) results.get("roleList");
    }

    @Override
    public ResultModule saveUserInfoAndPower(SysUserModel item, String token)
    {

        item.setSalt(StringUtil.getSalt());
        String secPass = MD5.encodePassword(item.getUserPass() + item.getSalt());
        item.setUserPass(secPass);
        item.setCreateTime(DateUtil.getCurrentTime());
        item.setUpdateTime(DateUtil.getCurrentTime());
        if (sysUserDao.insert(item) > 0)
        {
            SysUserModel userParam = new SysUserModel();
            userParam.setUserName(item.getUserName());
            userParam.setState(1);
            SysUserModel sysUserModel = sysUserDao.findOneByEntity(userParam);
            if (sysUserModel != null)
            {
                List<SysRoleModel> roleList = findRoleByToken(token);
                for (String id : item.getRoleIds().split(","))
                {
                    // 只有操作人有的角色才可以保存
                    if (containsRole(roleList, Long.valueOf(id)))
                    {
                        SysUserRoleModel sur = new SysUserRoleModel();
                        sur.setRoleId(Long.valueOf(id));
                        sur.setUserId(sysUserModel.getId());
                        sur.setUpdateTime(DateUtil.getCurrentTime());
                        sur.setCreateTime(DateUtil.getCurrentTime());
                        sysUserRoleDao.insert(sur);
                    }
                }
            }
            return ResultModule.success("保存成功");
        }
        else
        {
            return ResultModule.error("保存失败");
        }
    }

    private boolean containsRole(List<SysRoleModel> roleList, Long id)
    {
        for (SysRoleModel srm : roleList)
        {
            if (srm.getId().equals(id))
            {
                return true;
            }
        }
        return false;
    }
    @Override
    public ResultModule deleteUserLogic(SysUserModel item)
    {
        SysUserModel sysum = sysUserDao.selectByPrimaryKey(item.getId());
        if (sysum != null)
        {
            sysum.setState(9);
            if (sysUserDao.updateByPrimaryKey(sysum) > 0)
            {
                return ResultModule.success("保存成功");
            }
            else
            {
                return ResultModule.error("保存失败");
            }
        }
        else
        {
            return ResultModule.error("用户不存在");
        }
    }

    @Override
    public List<SysRoleModel> findRoleByUserId(Long id)
    {
        return sysUserDao.findRoleByUserId(id);
    }
}
