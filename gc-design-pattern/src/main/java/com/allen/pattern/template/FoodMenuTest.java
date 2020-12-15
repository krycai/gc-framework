package com.allen.pattern.template;

/**
 * @ClassName FoodMenuTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 18:07
 **/
public class FoodMenuTest {

    public static void main(String[] args){
        FoodMenu menu = new BaoCaiaiFoodMenu();
        menu.process();

        FoodMenu meat = new MeatFoodMenu();
        meat.process();
    }

}
