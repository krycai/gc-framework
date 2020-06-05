package com.allen.sys.controller;

import cn.com.bluemoon.qy.pojo.dto.SysRoleFormDto;
import cn.com.bluemoon.qy.pojo.dto.SysRolePageDto;
import cn.com.bluemoon.qy.pojo.vo.SysUserRoleVo;
import com.allen.sys.annotation.MethodLog;
import com.allen.sys.model.vo.SysRoleVo;
import com.allen.sys.result.ResponseBean;
import com.allen.sys.result.ResponseBeanUtil;
import com.allen.sys.service.SysRoleService;
import com.bluemoon.pf.standard.constants.ResponseCodeEnum;
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
    public ResponseBean<PageInfo<SysRole>> list() {
        PageInfo<SysRole> rolePage = sysRoleService.findRolePage();
        return ResponseBeanUtil.createScBean(rolePage);
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
        return ResponseBeanUtil.createScBean(allRoleList);
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
        return ResponseBeanUtil.createScBean(role);
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
        return ResponseBeanUtil.createScBean(sysRole);
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
        return ResponseBeanUtil.createScBean("删除成功");
    }

    /**
     * get user by roleId
     * @param roleId
     * @return
     */
    @MethodLog(content = "通过角色ID获取用户接口")
    @GetMapping(value = "/role/getUserByRoleId")
    public ResponseBean getUserByRoleId(String roleId){
        //参数校验
        List<SysUserRoleVo> list = sysRoleService.getUserRoleListByRoleId(roleId);
        return ResponseBeanUtil.createScBean(list);
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
            return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
        }
        //参数校验
        sysRoleService.deleteUserRole(roleId,userId);
        return ResponseBeanUtil.createScBean();
    }

    @MethodLog(content = "保存角色与用户关系接口")
    @PostMapping(value = "/role/submitUserRole")
    public ResponseBean submitUserRole(@RequestBody SysUserRole sysUserRole){
        if (null == sysUserRole){
            return ResponseBeanUtil.createFailBean(ResponseCodeEnum.FAIL);
        }
        Integer integer = sysRoleService.submitUserRole(sysUserRole);
        return ResponseBeanUtil.createScBean(integer);
    }
}
