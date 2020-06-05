package com.allen.sys.controller;

import cn.com.bluemoon.common.web.security.WebUtils;
import cn.com.bluemoon.qy.pojo.dto.AuthUser;
import com.alibaba.fastjson.JSONObject;
import com.allen.sys.annotation.MethodLog;
import com.allen.sys.model.vo.SysMenuVo;
import com.allen.sys.result.ResponseBean;
import com.allen.sys.result.ResponseBeanUtil;
import com.allen.sys.result.ResponseCodeEnum;
import com.allen.sys.service.SysMenuService;
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
        AuthUser user = WebUtils.getCurrentUser();
        //获取菜单原始数据
        List<SysMenu> oldList = sysMenuService.getMenuListByUserId(user.getId());
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
        return ResponseBeanUtil.createScBean(result);
    }

    /**
     * Gets menu tree.
     *
     * @return the menu tree
     */
    @MethodLog(content = "获取菜单树接口")
    @GetMapping(value = "/menu/tree")
    public ResponseBean getMenuTree() {
        AuthUser user = WebUtils.getCurrentUser();
        List<SysMenu> menuTree = sysMenuService.getMenuTree(user.getId());
        return ResponseBeanUtil.createScBean(menuTree);
    }

    /**
     * Gets menu list.
     *
     * @return the menu list
     */
    @MethodLog(content = "获取菜单列表接口")
    @GetMapping(value = "/menu/list")
    public ResponseBean getMenuList() {
        AuthUser user = WebUtils.getCurrentUser();
        List<SysMenu> menuList = sysMenuService.getMenuList(user.getId());
        return ResponseBeanUtil.createScBean(menuList);
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
            return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
        }
        sysMenuService.deleteMenuById(menuId);
        return ResponseBeanUtil.createScBean();
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
        return ResponseBeanUtil.createScBean(menu);
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
        return ResponseBeanUtil.createScBean(sysMenu);
    }

}
