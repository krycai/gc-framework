package com.allen.sys.model.dto;

import java.io.Serializable;

/**
 * @author: allen小哥 2020-06-06 07:54
 **/
public class UserPwdForm implements Serializable {
    private static final long serialVersionUID = 7893568289633215087L;
    private Integer id;

    private String password;

    private String oldPassword;

    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserPwdForm{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
