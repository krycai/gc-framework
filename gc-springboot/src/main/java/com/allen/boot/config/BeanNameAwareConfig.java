package com.allen.boot.config;

import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by xuguocai on 2021/3/25 17:12
 *
 * 设置bean的名字
 *
 * 让Bean获取自己在BeanFactory配置中的名字（根据情况是id或者name）
 */
public class BeanNameAwareConfig implements BeanNameAware {

    @Override
    public void setBeanName(String s) {
        System.out.println("---------BeanNameAware---------");
    }
}
