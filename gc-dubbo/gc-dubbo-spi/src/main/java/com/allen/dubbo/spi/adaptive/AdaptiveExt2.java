package com.allen.dubbo.spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author Allen 2021/4/16 20:36  作为需要被扩展的接口，注意要加上@SPI注解
 *
 * https://www.jianshu.com/p/dc616814ce98
 *
 * https://blog.csdn.net/qq_35190492/article/details/108256452
 **/
@SPI("dubbo")
public interface AdaptiveExt2 {

    @Adaptive({"t"})
    String echo(String msg, URL url);
}
