package com.allen.dubbo.spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;

/**
 * @Author Allen 2021/4/16 20:40
 **/
@Adaptive
public class ThriftAdaptiveExt2 implements AdaptiveExt2 {
    @Override
    public String echo(String msg, URL url) {
        System.out.printf("--------ThriftAdaptiveExt2---------");
        return "thrift";
    }
}
