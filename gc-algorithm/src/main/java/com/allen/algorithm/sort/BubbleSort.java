package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/4/26 14:42 冒泡排序  --> 设置遍历次数  接着遍历左右大小  然后交互左右位置。
 */
public class BubbleSort implements IArraySort {

    @Test
    public void test() throws Exception{
        int[] arr = {49,38,65,97,76,13,27,49,55,4};
        System.out.println(Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println("---"+Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
        return arr;
    }

}
