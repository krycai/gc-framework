package com.allen.sys.service.impl;

import cn.com.bluemoon.mybatis.api.Paging;
import com.allen.sys.mapper.SysRoleMenuMapper;
import com.allen.sys.service.SysRoleMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public SysRoleMenu get(Integer roleId) {
        return sysRoleMenuMapper.get(roleId);
    }

    @Override
    public PageInfo<SysRoleMenu> findSysRoleMenuPage(Paging page, SysRoleMenu sysRoleMenu) {
        // 执行分页查询
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());

        List<SysRoleMenu> list = sysRoleMenuMapper.findList(sysRoleMenu);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.update(sysRoleMenu);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteById(Integer roleId) {
        return sysRoleMenuMapper.deleteById(roleId);
    }

}
