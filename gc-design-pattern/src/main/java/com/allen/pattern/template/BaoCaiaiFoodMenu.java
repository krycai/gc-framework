package com.allen.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName BaoCaiaiFoodMenu
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/20 18:02
 **/
@Slf4j
public class BaoCaiaiFoodMenu extends FoodMenu {

    @Override
    void pourVetetable() {
        log.info("开始倒包菜");
    }

    @Override
    void addSource() {
        log.info("加辣椒作为包菜配料");
    }
}
