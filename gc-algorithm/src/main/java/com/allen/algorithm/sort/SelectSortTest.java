package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/9 11:24  选择排序
 */
public class SelectSortTest {

    @Test
    public void selectSort(){
        int[] nums = {0,5,2,1,4,8,7};
        int length = nums.length;

        // 升序处理，最小的在左边既是下标为0.遍历数组 length -1 ，因为最后一个元素在前面的处理过程中已经是最大了，不需要处理。
        for (int i=0;i<length-1;i++){
            // 数组下标
            int index = i;
            // 遍历当前元素后面的元素数组，既是从 i+1 开始
            for (int j=i+1;j<length;j++){
                // 找出最小元素的下标,nums[index]存在 数组的第一个元素
                if (nums[j] < nums[index]){
                    index = j;
                }
            }
            // 本身已经是最小元素，则不处理。否则交换数组元素位置
            if (i != index){
                // 交换位置
                int tmp = nums[i];
                // 前面的下标，赋予找到最小元素下标
                nums[i] = nums[index];
                nums[index] = tmp;
            }

        }
        System.out.println(Arrays.toString(nums));

    }

}
