package com.allen.pattern.filter;

/**
 * @ClassName FilterTest
 * @Description TODO
 * 过滤器模式允许开发人员使用不同的标准来过滤一组对象，通过逻辑运算以解耦的方式把它们连接起来，这种类型的设计模式属于结构型的模式，它可以组合多个标准来获得单一的标准。
 *
 * 定义一个过滤器链和一个过滤器管理类，当请求到达时，先传递给管理类，管理类调用过滤器链过滤请求，并依次向下传递请求，当执行完最后一个过滤器时，再交给请求的目标处理类处理。
 *
 * 应用场景:
 * 当你有一组对象，需要通过不同条件或条件组合来筛选出符合标准的结果时，可以使用过滤器模式。
 * @Author Xu
 * @Date 2019/3/26 18:06
 **/
public class FilterTest {

    public static void main(String[] args){
        FilterManager manager = new FilterManager(new Target());
        manager.setFilter(new AuthenticationFilter());
        manager.setFilter(new LoginFilter());

        Client client = new Client();
        client.setFilterManager(manager);
        client.sendRequest("6666");

    }



}
