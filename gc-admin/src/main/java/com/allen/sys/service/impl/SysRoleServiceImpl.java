package com.allen.sys.service.impl;

import cn.com.bluemoon.mybatis.api.Paging;
import cn.com.bluemoon.qy.pojo.dto.SysRoleFormDto;
import com.allen.sys.mapper.SysMenuMapper;
import com.allen.sys.mapper.SysRoleMapper;
import com.allen.sys.mapper.SysRoleMenuMapper;
import com.allen.sys.mapper.SysUserRoleMapper;
import com.allen.sys.model.vo.SysRoleVo;
import com.allen.sys.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author: allen小哥 2020-04-14 11:19
 **/
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    /**
     * 系统角色Mapper
     */
    @Autowired
    private SysRoleMapper sysRoleMapper;
    /***
     * 角色菜单mapper
     */
    @Autowired
    SysRoleMenuMapper roleMenuMapper;
    /**
     * 系统菜单Mapper
     */
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Override
    public PageInfo<SysRole> findRolePage() {
        // 执行分页查询
//        PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
//        List<SysRole> list = sysRoleMapper.findRolePage(role.getName(),role.getCode());
        return new PageInfo<>(list);
    }

    @Override
    public List<SysRole> findAllRoleList() {
        return sysRoleMapper.findAllList();
    }

    @Override
    public SysRoleVo getRoleById(Integer roleId) {
        SysRoleVo roleVo = new SysRoleVo();
        SysRole role = sysRoleMapper.get(Long.valueOf(roleId));
        BeanUtils.copyProperties(role,roleVo);

        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        List<SysRoleMenu> list =null;
        if (role != null && role.getCode().equals("ROLE_ADMIN")) {
            list = new ArrayList<>();
            HashMap<String, Object> hashMap = Maps.newHashMap();
            hashMap.put("isAdmin",true);
            List<SysMenu> sysMenus = sysMenuMapper.findListByParam(hashMap);
            SysRoleMenu roleMenu = null;
            for (SysMenu sysMenu :sysMenus){
                roleMenu = new SysRoleMenu();
                roleMenu.setMenuId(sysMenu.getId());
                roleMenu.setRoleId(roleId);
                list.add(roleMenu);
            }
        }
        else if (role != null && !role.getCode().equals("ROLE_ADMIN")) {
            sysRoleMenu.setRoleId(role.getId());
            list = roleMenuMapper.findList(sysRoleMenu);
        }
        roleVo.setRoleMenus(list);

        return roleVo;
    }


    @Override
    public SysRole saveRole(SysRoleFormDto formDto) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(formDto,role);
        if ( role.getId() == null ) {
            role.preInsert();
            sysRoleMapper.insert(role);
        } else {
            // 更新角色数据
            role.preUpdate();
            sysRoleMapper.update(role);
            roleMenuMapper.deleteById(role.getId());
        }

        // 处理角色与菜单关联
        if (!StringUtils.isEmpty(formDto.getMenuIds())){
            List<String> menuIds = Arrays.asList(formDto.getMenuIds().split(","));
            SysRoleMenu roleMenu = null;
            for (String menuId : menuIds){
                roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(role.getId());
                roleMenu.setMenuId(Integer.valueOf(menuId));
                roleMenuMapper.insert(roleMenu);
            }
        }
        return role;
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setDelFlag(true);
        sysRole.setOpTime(new Date());
        sysRoleMapper.updateEntity(sysRole);
    }

    @Override
    public void deleteUserRole(Integer roleId, Integer userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(roleId);
        sysUserRole.setUserId(userId);
        userRoleMapper.delete(sysUserRole);
    }


    @Override
    public Integer submitUserRole(SysUserRole sysUserRole) {
        sysUserRole.setCreateTime(new Date());

//        检查表中是否有数据
        if(userRoleMapper.existAdminSysUserRole(sysUserRole.getRoleId(),sysUserRole.getUserId())>0){
//            有数据
            return 0;
        }else{
            int i = userRoleMapper.insert(sysUserRole);
            return i;
        }
    }
}
