package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/8 11:08  基数排序
 *
 * 基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或bin sort，顾名思义，它是透过键值的部份资讯，
 * 要排序的元素分配至某些“桶”中，藉以达到排序的作用，基数排序法是属于稳定性的排序，其时间复杂度为O (nlog(r)m)，其中r为所采取的基数，而m为堆数，
 * 在某些时候，基数排序法的效率高于其它的稳定性排序法。
 */
public class RadixSortTest {

    /**
     * 实现的：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
     * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
     *
     * 实现：1、定义二维数组，根据元素的位数  存元素的位置
     *      2、根据 步骤1 的结果，重新遍历，排序新的元素位置
     *      3、根据 步骤2 的结果，重新遍历，依次类推，最后得到想要的 排序数组
     *
     *      https://baike.baidu.com/item/%E5%9F%BA%E6%95%B0%E6%8E%92%E5%BA%8F/7875498?fr=aladdin
     */
    @Test
    public void test(){
        int[] number =
                {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
        // 数字的位数
        int d = 3;
        int k = 0;
        int n = 1;
        //控制键值排序依据在哪一位
        int m = 1;
        //数组的第一维表示可能的余数0-9,第二维表示出现的次数
        int[][] temp = new int[10][number.length];
        //数组order[i]用来表示该位是i的数的个数
        int[] order = new int[10];
        // 遍历数字的位数
        while(m <= d)
        {
            // 遍历数组元素
            for(int i = 0; i < number.length; i++)
            {
                // 根据位数取这个数字的余数
                int lsd = ((number[i] / n) % 10);
                // 二为数组表示多个值，todo 结合0-9及相同位数的次数确定数组元素
                temp[lsd][order[lsd]] = number[i];
//                System.out.println("lsd:"+lsd+"    order[lsd]:"+order[lsd]+"  number[i]: "+number[i]);
                // 位数出现次数+1
                order[lsd]++;
            }

            // 遍历 0-9 的元素
            for(int i = 0; i < 10; i++)
            {
                // 判断 位数出现的次数 大于 0
                if(order[i] != 0){
                    // 遍历 位数出现的次数
                    for(int j = 0; j < order[i]; j++)
                    {
                        // 找到数组元素对应的二维数组元素
                        number[k] = temp[i][j];
                        // 寻找下一个下标的元素
                        k++;
                    }
                }
                // 位数没有出现元素，则默认为 0
                order[i] = 0;
            }
            System.out.println(Arrays.toString(number));
            // 个位为 1，十位为 10，百位为 100 .。。
            n *= 10;
            // 元素下标从 0 开始
            k = 0;
            // 元素的位数增加
            m++;
        }

        // 获取排序后的数组
        for(int i = 0; i < number.length; i++)
        {
            System.out.print(number[i] + " ");
        }
    }

    @Test
    public void test2(){
        int[] arr =
                {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};

        int maxDigit = getMaxDigit(arr);
        int[] ints = radixSort(arr, maxDigit);
        System.out.println("基数排序:"+Arrays.toString(ints));
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    /**
     * 获取数组元素种最大的值
     * @param arr
     * @return
     */
    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    /**
     * 获取数组中元素做多的位数如 100 三位
     * @param num
     * @return
     */
    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        // 利用 /= 取整数，最后的结果为 0，结束循环
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        // 返回 位数
        return lenght;
    }

    /**
     * 排序算法
     * @param arr  数组   {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 100};
     * @param maxDigit  位数
     * @return
     */
    private int[] radixSort(int[] arr, int maxDigit) {
        // 位数，个位时取 10 ，十位时 取 100，百位时取 1000
        int mod = 10;
        // 位置计算，如个位 十位 百位 千位
        int dev = 1;
        // for 循环，遍历位数如 个位 十位 百位 。。。
        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            // mod * 2 定义数组长度
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                // [0-9]对应负数，[10-19]对应正数 ，bucket + 10 表示下标的位置
                int bucket = ((arr[j] % mod) / dev) + mod;
                // todo counter[bucket] 为什么这么存？
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);

            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
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
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

}
