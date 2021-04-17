package com.allen.dubbo.spi.adaptive;

import com.alibaba.dubbo.common.URL;

/**
 * @Author Allen 2021/4/16 20:39
 **/
public class SpringCloudAdaptiveExt2 implements AdaptiveExt2 {
    @Override
    public String echo(String msg, URL url) {
        System.out.printf("--SpringCloudAdaptiveExt2--");
        return "spring cloud";
    }
}
