package com.allen.sys.model.dto;

import com.allen.sys.common.PageParam;

/**
 * @author xuguocai 2020/6/5 11:04
 */
public class SysDictParam extends PageParam {

    private String typeCode;

    private String typeName;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "SysDictParam{" +
                "typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
