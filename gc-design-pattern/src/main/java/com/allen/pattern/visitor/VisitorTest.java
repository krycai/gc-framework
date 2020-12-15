package com.allen.pattern.visitor;

import java.util.List;

/**
 * @ClassName VisitorTest
 * @Description TODO
 * @Author allen小哥
 * @Date 2019/4/1 15:13
 **/
public class VisitorTest {

    public static void main(String[] args){
        List<Element> list = ObjectStruture.getList();
        for (Element item :list){
            //item.doSomething();
            item.accept(new Visitor());
        }
    }

}
