package com.allen.sys.model.vo;

import java.io.Serializable;

/**
 * @author: allen小哥 2020-06-06 11:31
 **/
public class UserLoginVo implements Serializable {

    private static final long serialVersionUID = 7893568289633215087L;

    private String userName;

    private String loginName;

    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginVo{" +
                "userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
