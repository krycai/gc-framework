package com.allen.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName BaoCaiaiFoodMenu
 * @Description 具体子类。实现primitiveOperation()方法以完成算法中与特定子类相关的步骤。
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
