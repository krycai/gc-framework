package com.allen.pattern.filter;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName AuthenticationFilter
 * @Description 接口实现类
 * @Author Xu
 * @Date 2019/3/26 17:48
 **/
@Slf4j
public class AuthenticationFilter implements ParentFilter {
    @Override
    public void execute(String request) {
        // 过滤业务
      log.info("权限过滤器开始执行===》"+request);
    }
}
