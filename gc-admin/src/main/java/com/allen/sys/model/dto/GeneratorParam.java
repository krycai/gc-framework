package com.allen.sys.model.dto;

import com.allen.sys.common.PageParam;

/**
 * @author: allen小哥 2020-06-05 22:19
 **/
public class GeneratorParam extends PageParam {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "GeneratorParam{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
