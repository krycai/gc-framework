package com.allen.pattern.factory.singlemethod;

/**
 * @ClassName Reader
 * @Description TODO  抽象产品。所有的产品必须实现这个共同的接口，这样一来，使用这些产品的类既可以引用这个接口。而不是具体类 。
 * @Author Xu
 * @Date 2019/3/19 17:02
 **/
public interface Reader {

    void read();

}
