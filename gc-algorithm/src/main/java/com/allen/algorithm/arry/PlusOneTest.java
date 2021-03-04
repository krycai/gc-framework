package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuguocai on 2021/3/3 16:22
 *
 * 加一   给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 */
public class PlusOneTest {

    /**
     * 加一
     *  输入数组 ：{1,3,5}
     *  加一
     *  输出数组：{1,3,6}
     *
     *  输入的数字：135
     */
    @Test
    public void test1(){
        int[] nums = {9,9,9};

        // 数组长度
        int length = nums.length;
        int addon =0;
        for (int i=length-1;i >=0;i--){
            // 后面进位的处理方式
            nums[i]+=addon;
            addon = 0;
            // 获取最后一个元素，并加 1
            if (i == length-1){
                nums[i]++;
            }
            // 若元素满足 10，则向前进一位，addon 赋值 为 1
            if (nums[i] == 10){
                addon = 1;
                // 元素满10，则取0
                nums[i] = nums[i] % 10;
            }
        }

        // 若向前进一位超过原来的数组，则重新创建一个数组。首元素为 1
        List<Integer> list = new ArrayList<>();
        if (addon == 1){
            list.add(1);
            for (int i=0;i<length;i++){
                list.add(nums[i]);
            }
            System.out.println(list);
        }else {
            System.out.println(Arrays.toString(nums));
        }
    }
}
