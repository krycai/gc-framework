package com.allen.pattern.factory.abstractfactory;

/**
 * @ClassName AbstractFactioryTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 19:05
 **/
public class AbstractFactioryTest {

    public static void main(String[] args){
        // 定义Android 工厂
        SystemFactory factory = new AndroidSystemFactory();
        // PC 工厂
        SystemFactory pc = new PcSystemFactory();

        // 创建Android的操作系统
        OperationContronller web = factory.createOperationContronller();
        web.contron();
        // 创建 Android 的UI产品
        UIContronller ui = factory.createUIContronller();
        ui.display();

        // 创建 PC 的操作系统
        OperationContronller pcObj = pc.createOperationContronller();
        pcObj.contron();
        // 创建PC 的UI产品
        UIContronller pcUi = pc.createUIContronller();
        pcUi.display();

    }

}
