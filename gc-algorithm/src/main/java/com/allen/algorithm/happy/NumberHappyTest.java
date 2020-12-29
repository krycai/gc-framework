package com.allen.algorithm.happy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/29 9:44
 */
public class NumberHappyTest {

    @Test
    public void isHappy() throws Exception {
        NumberHappy happyNum = new NumberHappy() ;
        boolean happy = happyNum.isHappy(19);
        System.out.println("状态："+happy);
//        Assert.assertEquals(happy,true);
    }

    @Test
    public void isHappy2() throws Exception {
        NumberHappy happyNum = new NumberHappy() ;
        boolean happy = happyNum.isHappy(11);
        System.out.println("状态："+happy);
//        Assert.assertEquals(happy,false);
    }

    @Test
    public void isHappy3() throws Exception {
        NumberHappy happyNum = new NumberHappy() ;
        boolean happy = happyNum.isHappy(100);
        System.out.println("状态："+happy);
    }
}
