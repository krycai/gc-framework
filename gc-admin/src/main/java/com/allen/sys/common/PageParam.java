package com.allen.sys.common;

import java.io.Serializable;

/**
 * @author xuguocai 2020/6/5 11:01
 */
public class PageParam implements Serializable {
    private static final long serialVersionUID = 7L;

    private int pageNum;
    private int pageSize;
    private String orderBy;

    public PageParam() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageParam(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageParam(int pageNum, int pageSize, String orderBy) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
