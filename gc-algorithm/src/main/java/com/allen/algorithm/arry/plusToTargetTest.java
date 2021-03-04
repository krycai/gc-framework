package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.*;

/**
 * Created by xuguocai on 2021/3/4 9:26  两数之和
 */
public class plusToTargetTest {
    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 时间复杂度：O(n²)
     *   当数组的元素可以重复时，定义的 aa = i+"="+j; 为一组下标位置，存在多个元素下标
     *   当数组的元素不重复，则返回的是一组或者不存在的数组下标
     */
    @Test
    public void plusToTarget(){
        int[] nums = {2,4,6,4,2};
        int target = 6;
        int length = nums.length;
        List<String> list = new ArrayList<>();
        for (int i =0;i<length;i++){
            for (int j=i+1;j<length;j++){
                int tmp = nums[i]+nums[j];
                if (target == tmp){
                    // 数组
                    String  aa = i+"="+j;
                    list.add(aa);
                }
            }
        }

        System.out.println(list);
    }

    /**
     * 数组元素不重复
     *
     */
    @Test
    public void plusToTarget2(){
        long startTime = System.currentTimeMillis();
        int[] nums = {2,4,6};
        //单个数组下标
        int[] arr = {};
        int target = 6;
        int length = nums.length;
        for (int i =0;i<length;i++){
            for (int j=i+1;j<length;j++){
                int tmp = nums[i]+nums[j];
                if (target == tmp){
                    // 数组
                    arr = new int[]{i, j};
                }
            }
        }
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 时间复杂度为  O(n)
     */
    @Test
    public void plusToTarget3(){
        long startTime = System.currentTimeMillis();
        int[] nums = {2,4,6};
        //单个数组下标
        int[] arr = {};
        int target = 6;
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int i =0;i<length;i++){
            map.put(nums[i],i);
        }
        for (int i=0;i<length;i++){
            int tmp = target - nums[i];
            Integer val = map.get(tmp);
            if (val != null){
                arr = new int[]{i,val};
                break;
            }
        }
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 时间复杂度 小于 O(1)
     *
     *  利用 map 的特性： key：value  ,再根据key判断
     *
     */
    @Test
    public void plusToTarget4(){
        long startTime = System.currentTimeMillis();
        int[] nums = {2,4,6};
        //单个数组下标
        int[] arr = {};
        int target = 6;
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;

        for (int i=0;i<length;i++){
            int tmp = target - nums[i];
            boolean containsKey = map.containsKey(tmp);
            if (containsKey){
                Integer val = map.get(tmp);
                arr = new int[]{i,val};

            }
            map.put(nums[i],i);
        }
        System.out.println(System.currentTimeMillis()-startTime);
        System.out.println(Arrays.toString(arr));
    }
}
