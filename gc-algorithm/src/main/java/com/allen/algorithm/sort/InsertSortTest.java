package com.allen.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/9 16:07  插入排序
 */
public class InsertSortTest {

    @Test
    public void insertSort(){
        int[] nums = {0,5,2,1,4,8,7};
        int length = nums.length;
        // 默认第一个元素为有序元素，从第二个元素开始遍历，既是下标为 i=1
        for (int i=1;i<length;i++){
            // 遍历 待遍历数组元素与有序元素 组成的数组，既是从j=i,i-1,i-2,i-3...0 的下标倒序开始遍历
            for (int j=i;j>0;j--){
                // 比较元素，小元素则放到前面
                if (nums[j]< nums[j-1]){
                    // 元素位置交换
                    int tmp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void insertSort2(){
        int[] arr = {0,5,2,1,4,8,7};
        int length = arr.length;
        //  默认第一个元素为有序元素，从第二个元素开始遍历，既是下标为 i=1
        for (int i = 1; i < length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，逆序依次从右到左，找到比其小的数
            int j = i;
            // 遍历 待遍历数组元素与有序元素 组成的数组，既是从j=i,i-1,i-2,i-3...0 的下标倒序开始遍历
            // tmp < arr[j - 1] 表示 当前要比较的元素tmp比元素arr[j - 1]（这里的元素是多个：j=i,i-1,i-2,i-3...0）小
            while (j > 0 && tmp < arr[j - 1]) {
                // 元素大赋予元素小的（覆盖元素位置，比较暴力），此处是找到最小的元素
                System.out.println("tmp="+tmp+" j="+j+" arr[j]= "+arr[j]+" arr[j-1]="+arr[j-1]);
                arr[j] = arr[j - 1];
                j--;
            }
            System.out.println(Arrays.toString(arr)+"-------j="+j+"  tmp="+tmp);
            // 若是相等，则不操作。否则存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
            System.out.println(Arrays.toString(arr));

        }
        System.out.println("================================");
        System.out.println(Arrays.toString(arr));
    }

}
