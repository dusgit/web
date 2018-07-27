package com.yinrun.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.dao.SysRoleDao;
import com.yinrun.dao.SysRolePowerDao;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;
import com.yinrun.model.SysRolePowerModel;
import com.yinrun.model.SysUserRoleModel;
import com.yinrun.utils.DateUtil;
import com.yinrun.utils.PageHelperUtil;
import com.yinrun.utils.StringUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService
{

    @Autowired
    private SysRoleDao                                   sysRoleDao;

    @Autowired
    private SysRolePowerDao                              sysRolePowerDao;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Map<String, Object>> redis;

    @Override
    public SysRoleModel findById(Long id)
    {
        return sysRoleDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insert(SysRoleModel nam)
    {
        return sysRoleDao.insert(nam);
    }

    @Override
    public Integer update(SysRoleModel nam)
    {
        SysRoleModel sysRoleModel = sysRoleDao.selectByPrimaryKey(nam.getId());
        if (StringUtil.isNotEmpty(nam.getMark()) && !nam.getMark().equals(sysRoleModel.getMark()))
        {
            sysRoleModel.setMark(nam.getMark());
        }
        if (StringUtil.isNotEmpty(nam.getRoleName()) && !nam.getRoleName().equals(sysRoleModel.getRoleName()))
        {
            sysRoleModel.setRoleName(nam.getRoleName());
        }
        sysRoleModel.setUpdateTime(DateUtil.getCurrentTime());

        return sysRoleDao.updateByPrimaryKey(sysRoleModel);
    }

    @Override
    public ResultModule updateRoleInfoAndPower(SysRoleModel role)
    {
        SysRoleModel sysRoleModel = sysRoleDao.selectByPrimaryKey(role.getId());
        if (sysRoleModel == null)
        {
            return ResultModule.error("角色不存在");
        }
        if (StringUtil.isNotEmpty(role.getMark()) && !role.getMark().equals(sysRoleModel.getMark()))
        {
            sysRoleModel.setMark(role.getMark());
        }
        if (StringUtil.isNotEmpty(role.getRoleName()) && !role.getRoleName().equals(sysRoleModel.getRoleName()))
        {
            sysRoleModel.setRoleName(role.getRoleName());
        }
        // 设置更新时间
        sysRoleModel.setUpdateTime(DateUtil.getCurrentTime());
        Integer res = update(sysRoleModel);
        List<SysPowerModel> powerList = sysRoleDao.findPowerByRoleId(role.getId());
        String[] powerIds = null;
        if (StringUtil.isNotEmpty(role.getPowerIds()))
        {
            powerIds = role.getPowerIds().split(",");
            for (String powerId : powerIds)
            {
                boolean existPower = false;
                for (SysPowerModel sysPowerModel : powerList)
                {
                    if (sysPowerModel.getId() == Long.valueOf(powerId))
                    {
                        existPower = true;
                    }
                }
                if (!existPower)
                {
                    // 新增角色关联关系
                    SysRolePowerModel srpm = new SysRolePowerModel();
                    srpm.setRoleId(role.getId());
                    srpm.setPowerId(Long.valueOf(powerId));
                    srpm.setCreateTime(DateUtil.getCurrentTime());
                    srpm.setUpdateTime(DateUtil.getCurrentTime());
                    sysRolePowerDao.insert(srpm);
                }
            }
            for (SysPowerModel sysPowerModel : powerList)
            {
                boolean deletePower = true;
                for (String powerId : powerIds)
                {
                    if (sysPowerModel.getId().equals(Long.valueOf(powerId)))
                    {
                        deletePower = false;
                    }
                }
                if (deletePower)
                {
                    // 删除角色关联关系
                    sysRolePowerDao.deleteByRoleIdAndPowerId(role.getId(), sysPowerModel.getId());
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
    public Integer deleteById(Long id)
    {
        return sysRoleDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<SysRoleModel> findByExample(SysRoleModel item, PageVo page)
    {
        PageHelperUtil.startPage(page);
        Example example = new Example(SysRoleModel.class);
        Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(item.getMark()))
        {
            criteria.andCondition("mark = ", item.getMark());
        }
        if (StringUtil.isNotEmpty(item.getRoleName()))
        {
            criteria.andCondition("role_name = ", item.getRoleName());
        }
        if (item.getValidFlag() != null)
        {
            criteria.andCondition("valid_flag = ", item.getValidFlag());
        }
        List<SysRoleModel> list = sysRoleDao.selectByExample(example);
        return PageHelperUtil.getPaginateResult(page, list);
    }

    @Override
    public ResultModule deleteByRoleId(Long id)
    {
        // 查询是否有角色关联
        List<SysUserRoleModel> list = sysRoleDao.findUserRoleByRoleId(id);
        if (list != null && list.size() > 0)
        {
            return ResultModule.error("有用户绑定了此角色，请先解绑");
        }
        else
        {
            if (sysRoleDao.deleteByPrimaryKey(id) > 0)
            {
                return ResultModule.success("删除成功");
            }
            else
            {
                return ResultModule.error("");
            }
        }
    }

    @Override
    public ResultModule saveRoleInfoAndPower(SysRoleModel role)
    {
        role.setValidFlag(1);
        role.setCreateTime(DateUtil.getCurrentTime());
        role.setUpdateTime(DateUtil.getCurrentTime());
        if (sysRoleDao.insert(role) > 0)
        {
            SysRoleModel roleParam = new SysRoleModel();
            roleParam.setRoleName(role.getRoleName());
            roleParam.setMark(role.getMark());
            SysRoleModel sysRoleModel = sysRoleDao.selectOne(roleParam);
            if (sysRoleModel != null)
            {
                for (String id : role.getPowerIds().split(","))
                {
                    SysRolePowerModel sur = new SysRolePowerModel();
                    sur.setRoleId(sysRoleModel.getId());
                    sur.setPowerId(Long.valueOf(id));
                    sur.setUpdateTime(DateUtil.getCurrentTime());
                    sur.setCreateTime(DateUtil.getCurrentTime());
                    sysRolePowerDao.insert(sur);
                }
            }
            return ResultModule.success("保存成功");
        }
        else
        {
            return ResultModule.error("保存失败");
        }
    }

    @Override
    public List<SysPowerModel> findRolePowerList(SysRoleModel role)
    {
        return sysRoleDao.findPowerByRoleId(role.getId());
    }
}
