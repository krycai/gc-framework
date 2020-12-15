package com.allen.pattern.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName LoginFilter
 * @Description 接口实现类
 * @Author Xu
 * @Date 2019/3/26 17:49
 **/
@Slf4j
public class LoginFilter implements ParentFilter {
    @Override
    public void execute(String request) {
        // 过滤业务
      log.info("登录过滤器开始执行====》"+request);
    }
}
