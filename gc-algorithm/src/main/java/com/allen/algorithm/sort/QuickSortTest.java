package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/11 9:27  快速排序法
 *
 * https://baike.baidu.com/item/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95?fromtitle=%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F%E6%B3%95&fromid=612251
 */
public class QuickSortTest {

    @Test
    public void quickSort(){
        int[] arr = {49,38,65,97,76,13,27,49,55,4};
        System.out.println(Arrays.toString(arr));
        int[] sort = sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(sort));

    }

    /**
     *
     * @param nums {49,38,65,97,76,13,27,49,55,4}
     * @param start 数组下标
     * @param end  舒徐下标
     * @return
     */
    private int[] sort(int[] nums,int start,int end){
        // 临界值
        int tmp = nums[start];
        int i = start;
        int j = end;

        // 开始下标小于后面的下标
        while (i<j){
            // 若数组元素小于临界值，从左边开始移动查找，并记录数组下标 i
            while (i<j && nums[i] <tmp){
                i++;
            }
            // 若数组从右边开始处理，元素大于临界值，记录数组下标 j
            while (i<j&&nums[j] >tmp){
                j--;
            }
            // 若元素相等,数组元素向右移，既是 i++
            if (nums[i] == nums[j] && i<j){
                i++;
            }else {
                //交换元素位置
                int val = nums[i];
                nums[i] = nums[j];
                nums[j] = val;
            }
        }

        if (i-1>start){
            // 从左边开始的递归处理
            nums=sort(nums,start,i-1);
        }

        if (j+1<end){
            // 从右边开始的递归处理
            nums=sort(nums,j+1,end);
        }
        return (nums);
    }


}
