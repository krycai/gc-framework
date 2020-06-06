package com.allen.sys.model.dto;

import java.io.Serializable;

/**
 * @author: allen小哥 2020-06-06 09:44
 **/
public class LoginForm implements Serializable {
    private static final long serialVersionUID = 7893568289633215087L;

    private String loginName;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
