package com.allen.boot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by xuguocai on 2021/3/29 13:47
 *
 * springBoot整体框架就是通过该监听器org.springframework.boot.SpringApplicationRunListeners，来触发上下文监听器。
 * 通过上下文监听器来完成整体逻辑，比如加载配置文件，加载配置类，初始化日志环境等。
 *
 * 利用 ApplicationContext 上下文本来处理
 *
 * 自定义 监听器
 */
public class AllenApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 此处可以引入spring的一些特性 或者 业务相关的bean进行操作
     */
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("-------监听器执行的事件-----");
    }
}
