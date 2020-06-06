package com.allen.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.allen.sys.annotation.MethodLog;
import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.constants.ResponseCodeEnum;
import com.allen.sys.model.po.SysMenu;
import com.allen.sys.model.po.SysUser;
import com.allen.sys.model.vo.SysMenuVo;
import com.allen.sys.service.SysMenuService;
import com.allen.sys.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: allen小哥 2020-04-14 11:11
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin/auth")
public class SysMenuContronller {

    @Autowired
    SysMenuService sysMenuService;

    /**
     * 获取用户登录之后的导航菜单
     * 2018-11-13 新调整，增加按钮级别的菜单权限，获取菜单的接口做了调整，除了返回菜单树以外，还返回了用户的permissions
     * @return
     */
    @MethodLog(content = "获取导航接口")
    @GetMapping(value = "/menu/nav")
    public ResponseBean getMenuNav() {
        JSONObject result = new JSONObject();
        //SysUseSysUser sysUser = new SysUser();
        //        sysUser.setAdminFlag(true);
        //        sysUser.setId(1);r sysUser = ThreadLocalUtil.get();
        SysUser sysUser = new SysUser();
        sysUser.setAdminFlag(true);
        sysUser.setId(1);
        System.out.println("==="+sysUser);
        //获取菜单原始数据
        List<SysMenu> oldList = sysMenuService.getMenuListByUserId(sysUser);
        //构建菜单树
        List<SysMenu> menuList = sysMenuService.makeTree(oldList, true);
        //获取菜单中的权限标识
        Set<String> perSet = new HashSet<String>();
        for(SysMenu menu : oldList) {
            if( !StringUtils.isEmpty(menu.getPermission()) ) {
                perSet.add(menu.getPermission());
            }
        }
        result.put("menus", menuList);
        result.put("permission", perSet);
        return ResponseBeanUtil.ok(result);
    }

    /**
     * Gets menu tree.
     *
     * @return the menu tree
     */
    @MethodLog(content = "获取菜单树接口")
    @GetMapping(value = "/menu/tree")
    public ResponseBean getMenuTree() {
//        SysUser sysUser = ThreadLocalUtil.get();
        SysUser sysUser = new SysUser();
        sysUser.setAdminFlag(true);
        sysUser.setId(1);
        List<SysMenu> menuTree = sysMenuService.getMenuTree(sysUser);
        return ResponseBeanUtil.ok(menuTree);
    }

    /**
     * Gets menu list.
     *
     * @return the menu list
     */
    @MethodLog(content = "获取菜单列表接口")
    @GetMapping(value = "/menu/list")
    public ResponseBean getMenuList() {
//        SysUser sysUser = ThreadLocalUtil.get();
        SysUser sysUser = new SysUser();
        sysUser.setAdminFlag(true);
        sysUser.setId(1);
        List<SysMenu> menuList = sysMenuService.getMenuList(sysUser);
        return ResponseBeanUtil.ok(menuList);
    }

    /**
     * Delete menu response entity.
     *
     * @param menuId
     * @return the response entity
     */
    @MethodLog(content = "删除菜单信息接口")
    @GetMapping(value = "/menu/delete")
    public ResponseBean deleteMenu(Integer menuId) {
        if (null == menuId){
            return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
        }
        sysMenuService.deleteMenuById(menuId);
        return ResponseBeanUtil.ok();
    }

    /**
     * Gets menu.
     *
     * @param menuId the menu id
     * @return the menu
     */
    @MethodLog(content = "获取菜单信息接口")
    @GetMapping(value = "/menu/getMenuInfo")
    public ResponseBean getMenu(Integer menuId) {
        SysMenuVo menu = sysMenuService.getMenuById(menuId);
        return ResponseBeanUtil.ok(menu);
    }

    /**
     * Save menu sys menu.
     *
     * @param  menu
     * @return the sys menu
     */
    @MethodLog(content = "保存菜单信息接口")
    @PostMapping(value = "/menu/edit")
    public ResponseBean saveMenu(@RequestBody SysMenu menu) {
        SysMenu sysMenu = sysMenuService.saveMenu(menu);
        return ResponseBeanUtil.ok(sysMenu);
    }

}
