package com.allen.sys.model.dto;

import com.allen.sys.common.PageParam;

/**
 * @author: allen小哥 2020-06-06 07:43
 **/
public class UserParam extends PageParam {

    private String loginName;

    private String name;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
