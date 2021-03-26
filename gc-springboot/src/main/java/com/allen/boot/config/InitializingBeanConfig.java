package com.allen.boot.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Created by xuguocai on 2021/3/25 15:27
 *
 * 为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法。
 *
 * 1：spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，或者在配置文件中同过init-method指定，两种方式可以同时使用
 * 2：实现InitializingBean接口是直接调用afterPropertiesSet方法，比通过反射调用init-method指定的方法效率相对来说要高点。但是init-method方式消除了对spring的依赖
 * 3：如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。
 *
 *
 */
//@Component
public class InitializingBeanConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----初始化的时候执行afterPropertiesSet----");
    }

}
