package com.allen.sys.model.dto;

import java.io.Serializable;

/**
 * @author: allen小哥 2020-06-06 07:58
 **/
public class UserRoleForm implements Serializable {
    private static final long serialVersionUID = 7893568289633215087L;

    private Integer userId;
    //角色ID
    private String roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
