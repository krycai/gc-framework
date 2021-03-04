package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.*;

/**
 * Created by xuguocai on 2021/3/4 9:58  三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组。
 */
public class PlusThreeTest {

    /**
     * 三层循环，时间复杂度大于 O(n)
     *
     */
    @Test
    public void plusToZero(){
        int[] nums = {1,3,-4,5,-6,-7,6};

        int length = nums.length;

        List<String> list = new ArrayList<>();
        for (int i=0;i<length;i++){
            int num1 = nums[i];
            for (int j = i+1;j<length;j++){
                int num2 = nums[j];
                for (int k = j+1;k<length;k++){
                    int num3 = nums[k];
                    int tmp = num1 + num2 + num3;
                    if (tmp == 0){
                        String str = num1+"#"+num2+"#"+num3;
                        list.add(str);
                    }
                }
            }

        }

        System.out.println(list);
    }

    /**
     * 1、采取固定一个数，同时用双指针来查找另外两个数的方式。所以初始化时，我们选择固定第一个元素（当然，这一轮走完了，这个蓝框框我们就要也往前移动），
     * 同时将下一个元素和末尾元素分别设上 left 和 right 指针。
     * 2、如果和大于0，那就说明 right 的值太大，需要左移。如果和小于0，那就说明 left 的值太小，需要右移。
     */
    @Test
    public void plusToZero2(){
        int[] nums = {-1, 0, 1, 2, -1, -4};
        // 排序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        int length = nums.length;
        List<List<Integer>> list = new ArrayList();
        for (int i=0;i<length;i++){
            // 作为另外两个元素的和
            int target =0 - nums[i];
            // 取左边数
            int left = i+1;
            int right = length -1;
            // nums[i] 作为基准元素。最小的元素大于1，其他元素都大于1，直接结束循环
            if (nums[i] >0){
                // 结束循环 todo 如果固定下来的数（上面蓝色框框）本身就大于 0，那三数之和必然无法等于 0。
                break;
            }
            // 跳过相同的元素,同时过滤元素数组
            if (i == 0|| nums[i] != nums[i-1]){
                // 左右指针位置
                while (left < right) {
                    // 判断三个数是否等于0
                    if (nums[left] + nums[right] == target) {
                        // 保存到数组
                        list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 判断 是否是相同的左边元素
                        while (left < right && nums[left] == nums[left + 1]){
                            // 左边 右移
                            left++;
                        }
                        // 判断是否是相同的右边元素
                        while (left < right && nums[right] == nums[right - 1]){
                            // 右边 左移
                            right--;
                        }
                        // 左边 右边都移动
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target){ // 左边的值过于小，需要右移
                        left++;
                    } else{ // nums[left] + nums[right] > target  右边的值过于大，需要左移
                        right--;
                    }
                }
            }
        }

        System.out.println(list);
    }

    /**
     * 四数之和
     *   固定前面两位，移动后面两个指针 left  right
     */
    @Test
    public void plusToZero3(){
        int[] nums = {-1, 0, 1, 2, -1, -4,0,0,1,4};
        // 排序
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        int length = nums.length;
        List<List<Integer>> list = new ArrayList();
        for (int i=0;i<length;i++){
            // 作为另外两个元素的和
            int target =0 - nums[i]-nums[i+1];
            // 取左边数
            int left = i+2;
            int right = length -1;
            // nums[i] 作为基准元素。最小的元素大于1，其他元素都大于1，直接结束循环
            if (nums[i] >0){
                // 结束循环 todo 如果固定下来的数（上面蓝色框框）本身就大于 0，那四数之和必然无法等于 0。
                break;
            }
            // 左右指针位置
            while (left < right) {
                // 判断四个数是否等于0
                if (nums[left] + nums[right] == target) {
                    // 保存到数组
                    list.add(Arrays.asList(nums[i],nums[i+1], nums[left], nums[right]));
                    // 判断 是否是相同的左边元素
                    while (left < right && nums[left] == nums[left + 1]){
                        // 左边 右移
                        left++;
                    }
                    // 判断是否是相同的右边元素
                    while (left < right && nums[right] == nums[right - 1]){
                        // 右边 左移
                        right--;
                    }
                    // 左边 右边都移动
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < target){ // 左边的值过于小，需要右移
                    left++;
                } else{ // nums[left] + nums[right] > target  右边的值过于大，需要左移
                    right--;
                }
            }
        }

        System.out.println(list);
    }
}
