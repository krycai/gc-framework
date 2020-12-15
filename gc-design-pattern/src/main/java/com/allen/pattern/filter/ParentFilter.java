package com.allen.pattern.filter;

/**
 * @ClassName ParentFilter
 * @Description 过滤接口
 * 主要用于统一接收并过滤客户端的请求，根据过滤器的选择，将请求传送给对应的目标处理程序。
 * @Author Xu
 * @Date 2019/3/26 17:47
 **/
public interface ParentFilter {

    public void execute(String request);

}
