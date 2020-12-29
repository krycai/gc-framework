package com.allen.algorithm.link;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by xuguocai on 2020/12/29 11:08
 */
public class TwoSumTest {
    @Test
    public void getTwo1() throws Exception {
        TwoSum twoSum = new TwoSum() ;
        int[] nums ={1,3,5,7,8,9,11,33,55,66,77,10,2,55,24};
        int[] two1 = twoSum.getTwo1(nums, 13);
        System.out.println(JSON.toJSONString(two1));

    }

    @Test
    public void getTwo2(){
        TwoSum twoSum = new TwoSum() ;
        int[] nums ={1,3,5,7,8,9,11,33,55,66,77,10,2,55,24};
        int[] two = twoSum.getTwo2(nums, 13);
        System.out.println(JSON.toJSONString(two));

    }
}
