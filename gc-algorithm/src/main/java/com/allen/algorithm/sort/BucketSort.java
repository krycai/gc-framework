package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/4/26 14:03  桶排序  ---》思想类似于 计数排序 处理
 */
public class BucketSort implements IArraySort{

    // 利用插入排序
    private static final InsertSort insertSort = new InsertSort();

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

        return bucketSort(arr, 5);
    }

    /**
     *
     * @param arr  数组
     * @param bucketSize  桶大小
     * @return
     * @throws Exception
     */
    private int[] bucketSort(int[] arr, int bucketSize) throws Exception {
        if (arr.length == 0) {
            return arr;
        }
        // 定义最大及最小的 值
        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                // 取出最小的值
                minValue = value;
            } else if (value > maxValue) {
                // 取出最大的值
                maxValue = value;
            }
        }
        // 定义桶次数
        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        // 定义二维数组
        int[][] buckets = new int[bucketCount][0];

        // 利用映射函数将数据分配到各个桶中
        for (int i = 0; i < arr.length; i++) {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            // 对每个桶进行排序，这里使用了插入排序
            bucket = insertSort.sort(bucket);
            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrAppend(int[] arr, int value) {
        // 扩容一位
        arr = Arrays.copyOf(arr, arr.length + 1);
        // 给数组最后一位赋值 元素
        arr[arr.length - 1] = value;
        return arr;
    }

}
