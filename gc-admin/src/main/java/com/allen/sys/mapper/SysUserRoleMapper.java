package com.allen.sys.mapper;

import com.allen.sys.model.po.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {

    /**
     * 校验用户是否被授权
     * @param roleId
     * @param userId
     * @return
     */
    Integer existAdminSysUserRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
}