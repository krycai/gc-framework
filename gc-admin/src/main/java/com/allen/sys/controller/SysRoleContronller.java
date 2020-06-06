package com.allen.sys.controller;

import com.allen.sys.annotation.MethodLog;
import com.allen.sys.common.ResponseBean;
import com.allen.sys.common.ResponseBeanUtil;
import com.allen.sys.constants.ResponseCodeEnum;
import com.allen.sys.model.dto.SysRoleFormDto;
import com.allen.sys.model.dto.roleParam;
import com.allen.sys.model.po.SysRole;
import com.allen.sys.model.po.SysUserRole;
import com.allen.sys.model.vo.SysRoleVo;
import com.allen.sys.model.vo.SysUserRoleVo;
import com.allen.sys.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: allen小哥 2020-04-14 11:14
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin/auth")
public class SysRoleContronller {

    @Autowired
    SysRoleService sysRoleService;
    /**
     * List page info.
     *
     * @return the page info
     */
    @MethodLog(content = "获取角色列表接口")
    @PostMapping(value = "/role/list")
    public ResponseBean<PageInfo<SysRole>> list(roleParam param) {
        PageInfo<SysRole> rolePage = sysRoleService.findRolePage(param);
        return ResponseBeanUtil.ok(rolePage);
    }

    /**
     * All list.
     *
     * @return the list
     */
    @MethodLog(content = "获取所有角色接口")
    @GetMapping(value = "/role/all")
    public ResponseBean all() {
        List<SysRole> allRoleList = sysRoleService.findAllRoleList();
        return ResponseBeanUtil.ok(allRoleList);
    }

    /**
     * Gets role.
     *
     * @param roleId the role id
     * @return the role
     */
    @MethodLog(content = "获取角色信息接口")
    @GetMapping(value = "/role/view")
    public ResponseBean getRole(Integer roleId) {
        SysRoleVo role = sysRoleService.getRoleById(roleId);
        return ResponseBeanUtil.ok(role);
    }

    /**
     * Save role sys role.
     *
     * @param role the role
     * @return the sys role
     */
    @MethodLog(content = "保存角色信息接口")
    @PostMapping(value = "/role/edit")
    public ResponseBean saveRole(@Valid @RequestBody SysRoleFormDto role) {
        SysRole sysRole = sysRoleService.saveRole(role);
        return ResponseBeanUtil.ok(sysRole);
    }

    /**
     * Delete response entity.
     *
     * @param roleId the role id
     * @return the response entity
     */
    @MethodLog(content = "删除角色信息接口")
    @GetMapping(value = "/role/delete")
    public ResponseBean delete(Integer roleId) {
        sysRoleService.deleteRoleById(roleId);
        return ResponseBeanUtil.ok("删除成功");
    }

    /**
     * delete user_role by userId and roleId
     * @param roleId
     * @param userId
     * @return
     */
    @MethodLog(content = "删除角色与用户关系接口")
    @GetMapping(value = "/role/deleteUserRole")
    public ResponseBean deleteUserRole(Integer roleId,Integer userId){
        if (roleId == null || userId == null){
            return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
        }
        //参数校验
        sysRoleService.deleteUserRole(roleId,userId);
        return ResponseBeanUtil.ok();
    }

    @MethodLog(content = "保存角色与用户关系接口")
    @PostMapping(value = "/role/submitUserRole")
    public ResponseBean submitUserRole(@RequestBody SysUserRole sysUserRole){
        if (null == sysUserRole){
            return ResponseBeanUtil.fail(ResponseCodeEnum.FAIL);
        }
        Integer integer = sysRoleService.submitUserRole(sysUserRole);
        return ResponseBeanUtil.ok(integer);
    }
}
