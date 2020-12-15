package com.allen.pattern.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FilterManager
 * @Description 过滤器管理类
 * @Author Xu
 * @Date 2019/3/26 17:58
 **/
@Slf4j
public class FilterManager {

    FilterChain filterChain;

    public FilterManager(Target target){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(ParentFilter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request){
        filterChain.execute(request);
    }

}
