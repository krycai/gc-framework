package com.allen.pattern.proxy;

/**
 * @ClassName ProxyTest
 * @Description 客户端
 *
 * 优点： 1、职责清晰。 2、高扩展性。 3、智能化。
 *
 * 缺点：
 * 1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。
 * 2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
 *
 * 使用场景：按职责来划分，通常有以下使用场景：
 * 1、远程代理。
 * 2、虚拟代理。
 * 3、Copy-on-Write 代理。
 * 4、保护（Protect or Access）代理。
 * 5、Cache代理。
 * 6、防火墙（Firewall）代理。
 * 7、同步化（Synchronization）代理。
 * 8、智能引用（Smart Reference）代理。
 *
 * 注意事项：
 * 1、和适配器模式的区别：适配器模式主要改变所考虑对象的接口，而代理模式不能改变所代理类的接口。
 * 2、和装饰器模式的区别：装饰器模式为了增强功能，而代理模式是为了加以控制。
 *
 *
 *
 * @Author Xu
 * @Date 2019/3/20 17:10
 **/
public class ProxyTest {

    public static void main(String[] args){
        Subject proxy = new ProxySubject();
        proxy.buyApple();
    }

}
