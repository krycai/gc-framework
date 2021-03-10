package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/8 9:12  冒泡排序法
 *
 * 冒泡排序算法的原理如下： 
 *
 *  1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。 
 *
 * 2、对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。 
 *
 * 3、针对所有的元素重复以上的步骤，除了最后一个。 
 *
 * 4、持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 *
 *
 */
public class MaoPaoTest {

    @Test
    public void test1(){
        int[] nums = {5,2,1,9,6,4};
        int length = nums.length;
        // 元素一前一后变动元素位置
        for (int i=0;i< length;i++){
            for (int j=0;j<length-1;j++){
                // 前后位置比较
                if (nums[j] >nums[j+1]){
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                }
            }
        }

        System.out.println("冒泡排序法:"+ Arrays.toString(nums));
    }

    @Test
    public void test2(){
        int[] nums = {9,1,10,2,8};
        int length = nums.length;

        // 第一个for 旨在遍历所有元素
        for (int i=0;i< length;i++){
            // true 表示两个元素已经不需要比较，则退出遍历
            boolean flag = true;
            // 第二个 for 旨在元素一前一后变动元素位置（元素比较）。length -1-i: -1 表示最后一个元素不需要比较。-i表示遍历i次后的元素i个不需要比较，因为每次遍历，都将大的往后移，最后面的元素都是大的
            for (int j=0;j<length -1-i;j++){
                // 前后位置比较
                if (nums[j] >nums[j+1]){
                    // 定临时值tmp
                    int tmp = nums[j];
                    // 相邻的元素交换位置，既是第一个位置被赋予第二个位置的元素
                    nums[j] = nums[j+1];
                    // 后面的元素被赋予前面的元素。至此，两个元素的位置交换完毕
                    nums[j+1] = tmp;

                    //false  表示元素之间还可以比较
                    flag = false;
                }
            }
            // 若无排序，则跳出
            if (flag){
                break;
            }
        }

        System.out.println("冒泡排序法:"+ Arrays.toString(nums));
    }

}
