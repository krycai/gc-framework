package com.allen.dubbo.spi.adaptive;

import com.alibaba.dubbo.common.URL;

/**
 * @Author Allen 2021/4/16 20:39
 **/
public class DubboAdaptiveExt2 implements AdaptiveExt2 {
    @Override
    public String echo(String msg, URL url) {
        System.out.printf("---DubboAdaptiveExt2---");
        return "dubbo";
    }
}
