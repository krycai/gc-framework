package com.allen.sys.model.po;

import java.io.Serializable;

/**
 * 
 * @Description  列属性
 *
 * @author Guoqing
 * @Date 2018年1月15日
 */
public class SysTable implements Serializable {
	private static final long serialVersionUID = 1445403870473532561L;
	//表名
    private String tableName;
    //表类型
    private String engine;
    //表备注
    private String tableComment;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

}
