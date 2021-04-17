package com.allen.dubbo.spi.adaptive;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @Author Allen 2021/4/16 20:58
 **/
public class SpiTest {

    @Test
    public void test1() {
        ExtensionLoader<AdaptiveExt2> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt2.class);
        AdaptiveExt2 adaptiveExtension = loader.getAdaptiveExtension();
//        AdaptiveExt2 extension = loader.getExtension("");
        URL url = URL.valueOf("test://localhost/test");
        System.out.println(adaptiveExtension.echo("d", url));
    }

    @Test
    public void test2() {
        ExtensionLoader<AdaptiveExt2> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt2.class);
        AdaptiveExt2 adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?adaptive.ext2=cloud");
        System.out.println(adaptiveExtension.echo("d", url));
    }

    @Test
    public void test3() {
        ExtensionLoader<AdaptiveExt2> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt2.class);
        AdaptiveExt2 adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?adaptive.ext2=cloud");
        System.out.println(adaptiveExtension.echo("d", url));
    }

    @Test
    public void test4() {
        ExtensionLoader<AdaptiveExt2> loader = ExtensionLoader.getExtensionLoader(AdaptiveExt2.class);
        AdaptiveExt2 adaptiveExtension = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?t=cloud");
        System.out.println(adaptiveExtension.echo("d", url));
    }
}
