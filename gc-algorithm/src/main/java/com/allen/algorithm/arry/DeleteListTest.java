package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xuguocai on 2021/3/3 9:00  原地删除  给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 *
 * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
 * 代码解释:
 * 　　Object src : 原数组
 *    int srcPos : 从元数据的起始位置开始
 * 　　Object dest : 目标数组
 * 　　int destPos : 目标数组的开始起始位置
 * 　　int length  : 要copy的数组的长度
 *
 *
 */
public class DeleteListTest {


    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *   重点：1、定位相同的值，找到值对应的数组下标。
     *        2、根据数组变动，定位新数组对应的下标值
     */
    @Test
    public void removeElement(){
        int[] nums = {1,2,3,4,2,6,7};
        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // 删除元素
        int val = 2;
        // 原数组长度，作为数组变动后index的数组下标定位取值
        int length = numslist.size();
        for (int i=0;i<numslist.size();i++){
            int tmp = nums[i];
            // 找到相同的值
            if (val == tmp){
                System.out.println(numslist);
                // 判断数组长度是否变动
                if (length != numslist.size()){
                    numslist.remove(i-1);
                }else {
                    numslist.remove(i);
                }
                System.out.println("删除后的数组长度:"+numslist.size()+" index:"+i);
            }
        }
        System.out.println("数组长度:"+numslist.size()+" 最后原地删除后得到的数组"+numslist);
    }

    /**
     *  返回数组变动后的数组长度，可以实现，但返回数组，需要重新处理
     */
    @Test
    public void removeElement2(){
        int[] nums = {1,2,3,4,2,6,7};
        // 删除元素
        int val = 2;
        // 原数组长度，作为数组变动后index的数组下标定位取值
        int j = 0;
        for (int i=0;i<nums.length;i++){
            int tmp = nums[i];
            // 不相同的元素，则组成新的数组
            if (val != tmp){
                System.out.println(Arrays.toString(nums));
                nums[j] = nums[i];
                j++;
            }
        }
        System.out.println(j);
        System.out.println("最后原地删除后得到的数组"+Arrays.toString(nums));
        int[] ints = Arrays.copyOfRange(nums, 0, j);
        System.out.println("处理后的数组："+Arrays.toString(ints));
    }

    /**
     * 删除排序数组中的重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次。
     * 基于 list 处理：
     * 1、利用 map 存 重复的元素次数。
     * 2、遍历数组，找到重复元素的索引，利用数组索引删除
     */
    @Test
    public void removeSameElement(){
        int[] nums = {1,2,2,3,4,4,6,7};
        // 转换微list数组
        List<Integer> numslist = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // 利用map 存储 元素及元素出现的次数
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            Integer integer = map.get(nums[i]);
            if (integer == null){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],integer+1);
            }
        }

        System.out.println(Arrays.toString(nums));
        for (int i=0;i<numslist.size();i++){
            int tmp = numslist.get(i);
            Integer val = map.get(tmp);
            // 超过1 的重复元素，利用list 的remove删除方法
            if (val >1){
                numslist.remove(i);
            }
        }

        System.out.println("数组长度："+numslist.size()+"数组："+numslist);
    }

    /**
     * 根据前后元素不相等，重新处理元素数组的下标的元素
     */
    @Test
    public void removeSameElement2(){
        int[] nums = {1,2,2,3,4,4,6,7};
        // 变动的数组长度，刚开始初始化为 0
        int pos = 1;
        for(int i=1; i<nums.length; i++) {
            // 前后元素比较，不相等，重新处理数组元素下标的位置。
            if(nums[i] != nums[i-1]){
                nums[pos++] = nums[i];
            }
        }
        // 变动后的数组长度
        System.out.println(pos);
        System.out.println(Arrays.toString(nums));
        // 获取变动后的数组
        int[] ints = Arrays.copyOfRange(nums, 0, pos);
        System.out.println(Arrays.toString(ints));
    }
}
