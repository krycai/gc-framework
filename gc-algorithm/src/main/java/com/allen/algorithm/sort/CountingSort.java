package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/4/26 13:51  计数排序
 */
public class CountingSort implements IArraySort{

    @Test
    public void test() throws Exception{
        int[] arr = {49,38,65,97,76,13,27,4,49,55,4};
        System.out.println(Arrays.toString(arr));
        int[] sort = sort(arr);
        System.out.println(Arrays.toString(sort));
    }

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxValue = getMaxValue(arr);

        return countingSort(arr, maxValue);
    }

    private int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        // 定义新数组，用来存放 元素 作为 下标
        int[] bucket = new int[bucketLen];

        for (int value : arr) {
            // 数组中有重复的元素，新数组相同元素则统计多一次，既是 ++
            bucket[value]++;
        }

        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            //  bucket[j] > 0 说明新数组中存在原数组的元素
            while (bucket[j] > 0) {
                // 重新给数组下标 赋值
                arr[sortedIndex++] = j;
                // 减一：表示看看是否存在相同的元素
                bucket[j]--;
            }
        }
        return arr;
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

}
