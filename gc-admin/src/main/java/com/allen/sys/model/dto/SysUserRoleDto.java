package com.allen.sys.model.dto;

import java.io.Serializable;

/**
 * @author xuguocai 2020/4/15 15:22
 */
public class SysUserRoleDto implements Serializable {
    private static final long serialVersionUID = 7893568289633215087L;

    private String userId;
    //角色ID
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
