package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/10 16:12  归并排序
 *
 */
public class MergeSortTest {

    @Test
    public void mergeSort(){
        int[] arr = {49,38,65,97,76,13,27,49,55,4};
        System.out.println(Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));
    }

    /**
     * 数组拆分多个数组，拆分规则：数组长度/2 .跳出规则为数组为一个元素，既是length < 2.  运用了递归处理
     * @param arr
     * @return
     */
    private int[] sort(int[] arr){
        int length =arr.length;
        if (length <2){
            return arr;
        }
        int middle = length/2;
        int[] left = Arrays.copyOfRange(arr,0,middle);
        int[] right = Arrays.copyOfRange(arr,middle,length);

        return merge(sort(left),sort(right));
    }

    /**
     *
     * 将两个已经排好序的数组合并为一个有序数组，这个过程占用内存，因为频繁创建新的数组去存储排好序的left right 数组
     *
     * @param left 左边数组，得到的是已经排好序的数组
     * @param right 右边数组，得到的是已经排好序的数组
     * @return
     */
    private int[] merge(int[] left,int[] right){
        // 定义临时数组
        int[] nums = new int[left.length+right.length];
        int i=0;

        while (left.length >0 && right.length >0){
            if (left[0] <=right[0]){
                nums[i++] = left[0];
                // 取第一个元素之外的所有元素
                left = Arrays.copyOfRange(left,1,left.length);
            }else {
                nums[i++] = right[0];
                // 取第一个元素之外的所有元素
                right = Arrays.copyOfRange(right,1,right.length);
            }
        }

        // 查看left 、right 是否还有其他元素
        while (left.length >0){
            nums[i++] = left[0];
            left = Arrays.copyOfRange(left,1,left.length);
        }

        while (right.length >0){
            nums[i++] = right[0];
            right = Arrays.copyOfRange(right,1,right.length);
        }
        return nums;
    }
}
