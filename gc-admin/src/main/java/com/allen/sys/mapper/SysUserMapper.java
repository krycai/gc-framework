package com.allen.sys.mapper;

import com.allen.sys.model.po.SysRole;
import com.allen.sys.model.po.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {

    List<SysUser> findUserPage(@Param("name") String name, @Param("loginName") String loginName);

    /**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    SysUser ssoLogin(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 删除用户角色关联数据
     *
     * @param userId
     * @return the int
     */
    int deleteUserRole(@Param("userId") Integer userId);

    /**
     * 获取用户已授权角色列表
     * @param userId
     * @return
     */
    List<SysRole> getUserRoleByUserId(@Param("userId") Integer userId);

    SysUser getUserMsgByToken(@Param("token") String token);
}