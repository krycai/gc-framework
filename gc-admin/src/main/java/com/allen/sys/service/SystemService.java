package com.allen.sys.service;

import cn.com.bluemoon.mybatis.api.Paging;
import cn.com.bluemoon.qy.pojo.dto.SysUserRoleDto;
import cn.com.bluemoon.qy.pojo.po.SysRole;
import cn.com.bluemoon.qy.pojo.po.SysUser;
import com.allen.sys.model.dto.SysUserRoleDto;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SystemService {

    /**
     * 根据登录名获取用户
     *
     * @param loginName 登录名
     * @return SysUser user by login name
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 通过sso登陆获取用户信息
     *
     * @param loginAccount
     * @return
     * @throws Exception
     */
    SysUser getSsoLoginUserByLoginAccount(String loginAccount) throws Exception;

    /**
     * 查询用户列表
     *
     * @param page 分页信息
     * @param user 用户
     * @return 分页数据 page info
     */
    PageInfo<SysUser> findUserPage(Paging page, SysUser user);

    /**
     * 查询用户
     *
     * @param userId 用户ID
     * @return 用户 user by id
     */
    SysUser getUserById(Integer userId);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return the sys user
     */
    SysUser saveUser(SysUser user);

    /**
     * 更新用户信息
     *
     * @param user 用户
     */
    void updateUserInfo(SysUser user);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    void deleteUserById(Integer userId);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param newPassword 密码
     */
    void updateUserPasswordById(Integer userId, String newPassword);

    /**
     * 获取用户的已授权角色列表
     *
     * @param userId
     * @return
     */
    List<SysRole> getUserRoleByUserId(int userId);

    /**
     * 增加授权信息
     *
     * @param userRoleDto
     */
    void saveUserRole(SysUserRoleDto userRoleDto);

    /**
     * 用户一定时间内登录次数限制
     *
     * @param account
     * @return
     */
    Map<String, Object> userLoginLimit(String account);
}
