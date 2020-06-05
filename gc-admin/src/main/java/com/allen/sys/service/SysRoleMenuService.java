package com.allen.sys.service;

import cn.com.bluemoon.mybatis.api.Paging;
import com.github.pagehelper.PageInfo;

/**
 * @author xuguocai 2020/6/1 10:43
 */
public interface SysRoleMenuService {

    //分页查询
    PageInfo<SysRoleMenu> findSysRoleMenuPage(Paging page, SysRoleMenu sysRoleMenu);

    SysRoleMenu get(Integer roleId);

    int insert(SysRoleMenu sysRoleMenu);

    int update(SysRoleMenu sysRoleMenu);

    int deleteById(Integer roleId);

}
