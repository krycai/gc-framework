package com.allen.sys.model.dto;

import com.allen.sys.common.PageParam;

/**
 * @author: allen小哥 2020-06-05 23:06
 **/
public class roleParam extends PageParam {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "roleParam{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
