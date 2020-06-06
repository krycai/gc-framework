package com.allen.sys.service.impl;

import com.allen.sys.mapper.SysMenuMapper;
import com.allen.sys.model.po.SysMenu;
import com.allen.sys.model.po.SysUser;
import com.allen.sys.model.vo.SysMenuVo;
import com.allen.sys.service.SysMenuService;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: allen小哥 2020-04-14 11:19
 **/
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
    /**
     * 系统菜单Mapper
     */
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenuNav(SysUser sysUser) {
        return makeTree(getMenuListByUserId(sysUser), true);
    }

    @Override
    public List<SysMenu> getMenuTree(SysUser sysUser) {
        return makeTree(getMenuListByUserId(sysUser), false);
    }

    @Override
    public List<SysMenu> getMenuList( SysUser sysUser) {
        List<SysMenu> resultList = new ArrayList<>();
        //按父子顺序排列菜单列表
        sortList(resultList, getMenuListByUserId(sysUser), "");
        return resultList;
    }

    /**
     * 菜单排序
     *
     * @param list       目标菜单列表
     * @param sourceList 原始菜单列表
     * @param parentId   父级编号
     */
    private void sortList(List<SysMenu> list, List<SysMenu> sourceList, String parentId) {
        sourceList.stream()
                .filter(menu -> menu.getParentId() != null && menu.getParentId().equals(parentId))
                .forEach(menu -> {
                    list.add(menu);
                    // 判断是否还有子节点, 有则继续获取子节点
                    sourceList.stream()
                            .filter(child -> child.getParentId() != null && child.getParentId().equals(menu.getId()))
                            .peek(child -> sortList(list, sourceList, menu.getId().toString()))
                            .findFirst();
                });
    }

    /**
     * 获得用户菜单列表，超级管理员可以查看所有菜单
     *
     * @param sysUser 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> getMenuListByUserId(SysUser sysUser) {
        //超级管理员
        Map<String,Object> map = Maps.newHashMap();
        map.put("isAdmin",sysUser.getAdminFlag());
        map.put("userId", sysUser.getId());
        List<SysMenu> list = sysMenuMapper.findListByParam(map);
        return list;
    }

    /**
     * 构建树形结构
     *
     * @param originals 原始数据
     * @param useShow   是否使用开关控制菜单显示隐藏
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> makeTree(List<SysMenu> originals, boolean useShow) {

        // 构建一个Map,把所有原始数据的ID作为Key,原始数据对象作为VALUE
        Map<String, SysMenu> dtoMap = new HashMap<>(16);
        //父节点信息
        Set<String> parentIdSet = new HashSet<>();
        for (SysMenu node : originals) {
            //父节点Id
            Integer parentId = node.getParentId();
            if (null != parentId) {
                parentIdSet.add(parentId + "");
            }
            // 原始数据对象为Node，放入dtoMap中。
            String id = node.getId().toString();
            if (node.getShow() || !useShow) {
                dtoMap.put(id, node);
            }
        }

        //判断dtoMap是否含有parentId。如果没有，添加进去
        Iterator<String> iterator = parentIdSet.iterator();
        while (iterator.hasNext()) {
            String parentId = iterator.next();
            if (!dtoMap.containsKey(parentId)) {
                SysMenu sysMenu = this.sysMenuMapper.selectByPrimaryKey(parentId);
                if ("1".equals(sysMenu.getDelFlag())) {
                    continue;
                }
                dtoMap.put(parentId, sysMenu);
            }
        }

        List<SysMenu> result = new ArrayList<>();
        for (Map.Entry<String, SysMenu> entry : dtoMap.entrySet()) {
            SysMenu node = entry.getValue();
            if (node.getParentId()== null) {
                // 如果是顶层节点，直接添加到结果集合中
                result.add(node);
            } else {
                // 如果不是顶层节点，有父节点，然后添加到父节点的子节点中
                SysMenu parent = dtoMap.get(node.getParentId().toString());
                if (null != parent) {
                    //找不到父节点，
                    parent.addChild(node);
                }
            }
        }
        //对菜单按照排序权重重新排序，升序
        Collections.sort(result, new Comparator<SysMenu>(){

            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                return o1.getSort() - o2.getSort();
            }

        });

        //对子节点重新排序
        for(SysMenu menu : result ) {
            List<SysMenu> menuList = menu.getChildren();
            Collections.sort(menuList, new Comparator<SysMenu>(){

                @Override
                public int compare(SysMenu o1, SysMenu o2) {
                    return o1.getSort() - o2.getSort();
                }

            });
        }

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteMenuById(Integer menuId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(menuId);
        sysMenu.setDelFlag(true);
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
    }

    @Override
    public SysMenuVo getMenuById(Integer menuId) {
        SysMenu sysMenu = sysMenuMapper.selectByPrimaryKey(menuId);
        SysMenuVo sysMenuVo = new SysMenuVo();
        BeanUtils.copyProperties(sysMenu,sysMenuVo);
        if (null != sysMenu.getParentId()){
            SysMenu parentMenu = sysMenuMapper.selectByPrimaryKey(sysMenu.getParentId());
            sysMenuVo.setParentName(parentMenu.getText());
        }
        return sysMenuVo;
    }

    @Override
    @Transactional(readOnly = false)
    public SysMenu saveMenu(SysMenu menu) {

        /**
         * 该方法应该分为4个分支考虑
         * 1、新增顶级菜单时，id,parentId 均为空，insert数据
         * 2、新增非顶级菜单时,id为空， parentId 非空，insert数据
         * 3、编辑顶级菜单时，id非空， parentId 为空，update数据
         * 4、编辑非顶级菜单时，id非空， parentId 非空，update数据
         */
        if( menu.getId() == null ){
            if( menu.getParentId() == null ){
                //新增顶级菜单
                menu.setCreateTime(new Date());
                menu.setUpdateTime(new Date());
                sysMenuMapper.insert(menu);
            }else{
                //新增非顶级菜单
                SysMenu parentMenu = sysMenuMapper.selectByPrimaryKey(menu.getParentId());
                //获取父菜单的parentIds
                String parentParentIds = parentMenu == null ? "" : parentMenu.getParentIds();
                String parentIds = parentParentIds==null?"":parentParentIds + parentMenu.getId() + ",";
                menu.setParentIds(parentIds);
                menu.setCreateTime(new Date());
                menu.setUpdateTime(new Date());
                sysMenuMapper.insert(menu);
            }
        }else{
            //编辑顶级菜单
            //编辑非顶级菜单
            menu.setUpdateTime(new Date());
            if( menu.getParentId() != null) {
                if (menu.getParentId() == 0) {
                    menu.setParentId(null);
                }
            }
            sysMenuMapper.updateByPrimaryKeySelective(menu);
        }
        return menu;
    }

}
