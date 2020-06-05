package com.allen.sys.service.impl;

import cn.com.bluemoon.mybatis.api.Paging;
import com.allen.sys.mapper.SysUserRoleMapper;
import com.allen.sys.service.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole get(Integer userId) {
        return sysUserRoleMapper.get(userId);
    }

    @Override
    public PageInfo<SysUserRole> findSysUserRolePage(Paging page, SysUserRole sysUserRole) {
        // 执行分页查询
        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());

        List<SysUserRole> list = sysUserRoleMapper.findList(sysUserRole);
        return new PageInfo<>(list);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(SysUserRole sysUserRole) {
        return sysUserRoleMapper.update(sysUserRole);
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteById(Integer userId) {
        return sysUserRoleMapper.deleteById(userId);
    }

}
