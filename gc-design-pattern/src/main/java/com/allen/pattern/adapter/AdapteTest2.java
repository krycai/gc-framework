package com.allen.pattern.adapter;

/**
 * @ClassName AdapteTest2
 * @Description 组合模式适配
 * @Author Xu
 * @Date 2019/3/20 16:36
 **/
public class AdapteTest2 {

    public static void main(String[] args){
        Target target= new Adapter2(new Adaptee());
        target.request();
    }
}
