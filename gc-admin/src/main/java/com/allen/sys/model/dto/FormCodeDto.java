package com.allen.sys.model.dto;

import java.io.Serializable;

/**
 * @author xuguocai 2020/4/15 9:30
 */
public class FormCodeDto implements Serializable {
    private static final long serialVersionUID = 7893568289633215087L;

    private String packageName;
    private String author;
    private String email;
    private String tables;
    // 生成类型：tk 生成器，bm 生成器
    private String type;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FormCodeDto{" +
                "packageName='" + packageName + '\'' +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                ", tables='" + tables + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
