package com.allen.pattern.facade;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName FacadeTest
 * @Description 客户端
 * 实现客户类与子系统类的松耦合
 * 降低原有系统的复杂度
 * 提高了客户端使用的便捷性，使得客户端无须关心子系统的工作细节，通过外观角色即可调用相关功能。
 *
 * 缺点：
 * 避免了系统与系统之间的高耦合度
 * 使得复杂的子系统用法变得简单
 * @Author Xu
 * @Date 2019/3/20 17:35
 **/
@Slf4j
public class FacadeTest {

    public static void main(String[] args){
        FacadeLight light = new FacadeLight();
        FacadeTelevision television = new FacadeTelevision();

        Facades facades = new Facades(light,television);

       log.info("晚上好,晚风阵阵");
        facades.on();
        log.info("good night");
        facades.off();

    }

}
