package com.allen.sys.service;

import cn.com.bluemoon.qy.pojo.po.SysMenu;
import cn.com.bluemoon.qy.pojo.vo.SysMenuVo;
import com.allen.sys.model.vo.SysMenuVo;

import java.util.List;

/**
 * @author: allen小哥 2020-04-14 11:19
 **/
public interface SysMenuService {
    /**
     * 查询用户导航菜单
     *
     * @param userId 用户ID
     * @return 菜单列表 menu nav
     */
    List<SysMenu> getMenuNav(Integer userId);

    /**
     * 查询用户菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表 menu list
     */
    List<SysMenu> getMenuList(Integer userId);

    /**
     * 查询用户菜单
     *
     * @param userId 用户ID
     * @return 菜单列表 menu tree
     */
    List<SysMenu> getMenuTree(Integer userId);

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     */
    void deleteMenuById(Integer menuId);

    /**
     * 查询菜单
     *
     * @param menuId 菜单ID
     * @return 菜单 menu by id
     */
    SysMenuVo getMenuById(Integer menuId);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return the sys menu
     */
    SysMenu saveMenu(SysMenu menu);

    /**
     * 获取用户菜单数据
     * @param userId
     * @return
     */
    List<SysMenu> getMenuListByUserId(Integer userId);

    /**
     * 构建树形菜单
     * @param originals
     * @param useShow
     * @return
     */
    List<SysMenu> makeTree(List<SysMenu> originals, boolean useShow);
}
