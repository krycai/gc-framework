package com.allen.pattern.proxy.cglib;

import com.allen.pattern.proxy.dynamic.HelloService;
import com.allen.pattern.proxy.dynamic.HelloServiceImpl;

/**
 * @author xuguocai 2020/2/18 18:40
 */
public class CglibMain {

    public static void main(String[] args) {
        CglibHelloService helloServiceCglib = new CglibHelloService();
        HelloService service =(HelloService) helloServiceCglib.getInstance(new HelloServiceImpl());
        service.sayHello("张三");
    }

}
