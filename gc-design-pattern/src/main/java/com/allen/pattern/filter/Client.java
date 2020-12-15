package com.allen.pattern.filter;

/**
 * @ClassName Client
 * @Description 客户端
 * @Author Xu
 * @Date 2019/3/26 18:04
 **/
public class Client {

    FilterManager filterManager;

    public void setFilterManager(FilterManager manager){
        this.filterManager = manager;
    }

    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }

}
