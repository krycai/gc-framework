package com.allen.pattern.adapter;

/**
 * @ClassName AdapterTest
 * @Description 客户端
 * @Author Xu
 * @Date 2019/3/20 16:31
 **/
public class AdapterTest {

    public static void main(String[] args){
        Target target = new Adapter();
        target.request();
    }


}
