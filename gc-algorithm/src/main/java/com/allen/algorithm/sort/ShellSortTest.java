package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/10 9:03   希尔排序  todo 方法 shell2 处理逻辑不是很懂
 */
public class ShellSortTest {

    @Test
    public void shell(){
        int[] nums = {49,38,65,97,76,13,27,49,55,04};
        int length = nums.length;
        System.out.println("遍历前:"+ Arrays.toString(nums));

        int len = length;
        while (true){
            // 每次分组递减量
            len /=2;
            System.out.println("len=="+len);
            // 遍历分组的元素
            for (int i=0;i<len;i++){
                //这个循环里其实就是一个插入排序
                for (int j=i+len;j<length;j +=len){
                    // 获取前面的元素的索引（下标）。 nums[tmpLen+len] 递增量后的元素
                    int tmpLen = j-len;
                    while (tmpLen >=0 && nums[tmpLen] > nums[tmpLen+len]){
                        // 元素交换位置如： tmpLen 与  tmpLen+len  交换位置。
                        int tmp = nums[tmpLen];
                        nums[tmpLen] = nums[tmpLen+len];
                        nums[tmpLen+len] = tmp;
                        tmpLen -= len;
                        System.out.println("tmpLen:"+tmpLen);
                    }
                    System.out.println("j=="+j);
                }
            }
            // 分组递减量为1时，则结束遍历
            if (len == 1){
                break;
            }
        }

        System.out.println("遍历后:"+ Arrays.toString(nums));

    }

    @Test
    public void shell2(){
        int[] arr = {49,38,65,97,76,13,27,49,55,4};
        int length = arr.length;
        System.out.println("遍历前:"+ Arrays.toString(arr));

        int gap = 1;
        while (gap < length/3) {
            gap = gap * 3 + 1;
        }
        System.out.println("gap:"+gap);
        // 分组遍历
        while (gap > 0) {
            // 元素遍历
            for (int i = gap; i < length; i++) {
                // 后面的元素 分组递增量：gap
                int tmp = arr[i];
                // 前面元素的索引
                int j = i - gap;
                // 元素大小比较
                while (j >= 0 && arr[j] > tmp) {
                    // arr[j] > tmp 前面元素大于后面元素，则前面元素暴力覆盖后面元素(j + gap)
                    arr[j + gap] = arr[j];
                    j -= gap;
                }

                arr[j + gap] = tmp;
            }
            // 对一个数进行下舍入
            gap = (int) Math.floor(gap / 3);
        }

        System.out.println("遍历后:"+ Arrays.toString(arr));

    }
}
