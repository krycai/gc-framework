package com.allen.boot.config;

import org.springframework.beans.factory.DisposableBean;

/**
 * Created by xuguocai on 2021/3/25 17:14
 *
 *  bean销毁时的时候会先执行destroy方法
 *
 */
public class DisposableBeanConfig implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("----------DisposableBean -------------");
    }
}
