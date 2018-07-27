package com.yinrun.service;

import java.util.List;

import com.yinrun.bean.PageResult;
import com.yinrun.bean.PageVo;
import com.yinrun.bean.ResultModule;
import com.yinrun.model.SysPowerModel;
import com.yinrun.model.SysRoleModel;

public interface SysRoleService extends BaseService<SysRoleModel>
{
    /**
     * 根据条件查询数据
     * @param item
     * @param page
     * @return
     * @author 张亮亮
     */
    public PageResult<SysRoleModel> findByExample(SysRoleModel item, PageVo page);

    /**
     * 根据主键删除角色
     * @param id
     * @return
     * @author 张亮亮
     */
    public ResultModule deleteByRoleId(Long id);

    /**
     * 保存角色和相关的权限
     * @param item
     * @return
     * @author 张亮亮
     */
    public ResultModule saveRoleInfoAndPower(SysRoleModel item);

    /**
     * 更新角色和权限
     * @param role
     * @return
     * @author 张亮亮
     */
    public ResultModule updateRoleInfoAndPower(SysRoleModel role);

    /**
     * 查询角色的权限
     * @param role
     * @return
     * @author 张亮亮
     */
    public List<SysPowerModel> findRolePowerList(SysRoleModel role);

}
