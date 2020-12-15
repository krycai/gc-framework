package com.allen.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName MeatFoodMenu
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 18:05
 **/
@Slf4j
public class MeatFoodMenu extends FoodMenu {

    @Override
    void pourVetetable() {
      log.info("加肉");
    }

    @Override
    void addSource() {
        log.info("加酱油");
    }
}
