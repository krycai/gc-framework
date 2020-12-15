package com.allen.pattern.filter;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FilterChain
 * @Description 组合过滤器类
 * @Author Xu
 * @Date 2019/3/26 17:53
 **/
@Slf4j
public class FilterChain {

    private List<ParentFilter> list = new ArrayList<>();
    private Target target;

    public void addFilter(ParentFilter filter){
        log.info("添加拦截器=="+filter);
        list.add(filter);
    }

    public void setTarget(Target target){
        this.target = target;
    }

    public void execute(String request){
        for (ParentFilter filter : list){
            filter.execute(request);
        }
        target.execute(request);
    }

}
