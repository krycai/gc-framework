package com.allen.sys.service;

import cn.com.bluemoon.mybatis.api.Paging;
import cn.com.bluemoon.qy.pojo.po.SysUserRole;
import com.github.pagehelper.PageInfo;

/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SysUserRoleService {

    //分页查询
    PageInfo<SysUserRole> findSysUserRolePage(Paging page, SysUserRole sysUserRole);

    SysUserRole get(Integer userId);

    int insert(SysUserRole sysUserRole);

    int update(SysUserRole sysUserRole);

    int deleteById(Integer userId);
}
