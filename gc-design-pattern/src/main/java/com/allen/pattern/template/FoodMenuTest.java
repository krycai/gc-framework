package com.allen.pattern.template;

/**
 * @ClassName FoodMenuTest
 * @Description 客户端操作
 * @Author Xu
 * @Date 2019/3/20 18:07
 **/
public class FoodMenuTest {

    public static void main(String[] args){
        // 包菜操作
        FoodMenu menu = new BaoCaiaiFoodMenu();
        menu.process();

        System.out.println("-------------------------------");
        // 肉类操作
        FoodMenu meat = new MeatFoodMenu();
        meat.process();
    }

}
