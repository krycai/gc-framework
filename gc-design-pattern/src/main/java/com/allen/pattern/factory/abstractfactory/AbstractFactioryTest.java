package com.allen.pattern.factory.abstractfactory;

/**
 * @ClassName AbstractFactioryTest
 * @Description TODO
 * @Author Xu
 * @Date 2019/3/19 19:05
 **/
public class AbstractFactioryTest {

    public static void main(String[] args){
        SystemFactory factory = new AndroidSystemFactory();
        SystemFactory pc = new PcSystemFactory();

        OperationContronller web = factory.createOperationContronller();
        web.contron();
        UIContronller ui = factory.createUIContronller();
        ui.display();

        OperationContronller pcObj = pc.createOperationContronller();
        pcObj.contron();
        UIContronller pcUi = pc.createUIContronller();
        pcUi.display();

    }

}
