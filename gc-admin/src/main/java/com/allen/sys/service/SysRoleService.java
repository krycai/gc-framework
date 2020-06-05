package com.allen.sys.service;

import cn.com.bluemoon.mybatis.api.Paging;
import cn.com.bluemoon.qy.pojo.dto.SysRoleFormDto;
import cn.com.bluemoon.qy.pojo.vo.SysUserRoleVo;
import com.allen.sys.model.vo.SysRoleVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: allen小哥 2020-04-14 11:18
 **/
public interface SysRoleService {

    /**
     * 查询角色列表
     *
     * @return 角色 page info
     */
    PageInfo<SysRole> findRolePage();

    /**
     * 获得所有角色列表
     *
     * @return 角色 list
     */
    List<SysRole> findAllRoleList();

    /**
     * 查询角色
     *
     * @param roleId 角色ID
     * @return 角色 role by id
     */
    SysRoleVo getRoleById(Integer roleId);

    /**
     * 保存角色
     *
     * @param role 角色
     * @return the sys role
     */
    SysRole saveRole(SysRoleFormDto role);

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     */
    void deleteRoleById(Integer roleId);

    /**
     * 通过角色ID获取角色用户
     * @param roleId
     * @return
     */
    public List<SysUserRoleVo> getUserRoleListByRoleId(String roleId);

    /**
     * delete user_role by roleId and userId
     * @param roleId
     * @param userId
     */
    public void deleteUserRole(Integer roleId, Integer userId);

    /**
     *  save userRole
     * @param sysUserRole
     * @return
     */
    public Integer submitUserRole(SysUserRole sysUserRole);

}
