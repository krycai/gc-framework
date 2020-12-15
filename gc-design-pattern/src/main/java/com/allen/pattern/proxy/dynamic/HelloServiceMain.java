package com.allen.pattern.proxy.dynamic;

/**
 * @author xuguocai 2020/2/18 18:22
 *
 * 测试类
 *
 */
public class HelloServiceMain {

    public static void main(String[] args) {
        // 代理对象
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
        HelloService proxy = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        proxy.sayHello("李四");
    }

}
