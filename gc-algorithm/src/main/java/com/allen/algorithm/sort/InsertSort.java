package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/4/26 14:34  插入排序
 */
public class InsertSort implements IArraySort {

    @Test
    public void test() throws Exception{
        int[] arr = {49,38,65,97,76,13,27,49,55,4};
        System.out.println(Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始 从右到左 比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 此处注意 j-- 后得到 j 的值，然后 j 于 i 交换位置
            if (j != i) {
                arr[j] = tmp;
            }

        }
        return arr;
    }

}
