package com.allen.pattern.visitor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName ObjectStruture
 * @Description 结构对象
 *
 * 一个元素的容器，一般包含一个容纳多个不同类、不同接口的容器，如List、Set、Map等，在项目中一般很少抽象出这个角色。
 *
 * @Author allen小哥
 * @Date 2019/4/1 15:07
 **/
@Slf4j
public class ObjectStruture {

    public static List getList(){
        List list = new ArrayList();
        Random random = new Random();
        for (int i =0; i < 5; i++){
            int temp = random.nextInt(100);
            if (temp > 5){
                list.add(new ConcreteElementA());
            } else {
                list.add(new ConcreteElementB());
            }
        }
        return list;
    }


}
