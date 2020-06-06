package com.allen.sys.mapper;

import com.allen.sys.model.po.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {

    List<SysRole> findRolePage(@Param("name") String name, @Param("code") String code);

    /**
     * 查询用户角色列表
     *
     * @param userId the user id
     * @return the list
     */
    List<SysRole> findListByUserId(@Param("userId") Integer userId);

}