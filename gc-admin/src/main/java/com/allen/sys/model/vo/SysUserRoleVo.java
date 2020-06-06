package com.allen.sys.model.vo;

import java.io.Serializable;
import java.util.Date;

public class SysUserRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

	private Integer userId;

    private Integer roleId;
	
    private String roleCode;

    private String createUser;

    private Date createTime;
    
    private String userName;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUserRoleVo{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                '}';
    }
}
