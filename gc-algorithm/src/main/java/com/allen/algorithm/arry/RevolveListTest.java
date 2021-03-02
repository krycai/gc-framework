package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.*;

/**
 * Created by xuguocai on 2021/3/2 17:11  旋转数组： 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
 */
public class RevolveListTest {

    /**
     * 方法一 、 方法三 、方法四
     *  原理：算出移动后的下标，计算移动位置k与数组长度length的关系。然后利用map存储移动后的位置与值关系
     */
    @Test
    public void revolveList1(){
        int[] aa = {1,2,3,4,5,6,7};

        int length = aa.length;
        // k 为移动的位置
        int k = 3;
        List<Integer> list = new ArrayList<>();
        // 移动位置 移动的值
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i< length;i++){
            // 移动的下标数
            int tmp = i+k;
            // 若下标数等于原数组长度，则为第一个元素既是下标为0
            if (tmp == length){
                tmp = 0;
            }else if (tmp > length){
                // 高位的下标减去数组长度，既是数组开始的下标数
                tmp = tmp-length;
            }

            // 变动后的位置与值
            map.put(tmp,aa[i]);
        }
        for (int j=0;j<length;j++){
            list.add(map.get(j));
        }
        System.out.println(list);
    }

    /**
     * 方法二 元素反转
     *
     * 若我们需要将数组中的元素向右移动 k 个位置， 那么 k%l (l为数组长度) 的尾部元素会被移动到头部，剩下的元素会被向后移动。
     */
    @Test
    public void revolveList2(){
        int[] aa = {1,2,3,4,5,6,7};
        // 数组长度
        int length = aa.length;
        // k 为移动的位置
        int k = 2;
        // 反转原数组
        reverse2(aa);
        // 获取移动到头部的元素个数。 k%l (l为数组长度) 的尾部元素会被移动到头部，其余的向后移
        int move = k%length;

        // 获取右边数组(向后移动的元素)
        int[] right = Arrays.copyOfRange(aa,move,length);
        System.out.println("right:"+Arrays.toString(right));

        //获取左边的数组（移动到头部的元素）
        int[] left = Arrays.copyOf(aa,move);
        System.out.println("left:"+Arrays.toString(left));
        // 移动到头部的元素数组反转
        reverse2(left);
        System.out.println("left反转:"+Arrays.toString(left));
        // 向后移动的数组元素反转
        reverse2(right);
        System.out.println("right反转:"+Arrays.toString(right));

        // 定义临时数组，存最新移动后的数组
        List<Integer> list = new ArrayList<>();
        for (int item:left){
            list.add(item);
        }

        for (int item : right){
            list.add(item);
        }

        System.out.println(list);
    }

    /**
     * 我们只需要将所有元素反转，然后反转前 k 个元素，再反转后面l-k个元素，就能得到想要的结果。
     * @param nums
     */
    private void reverse2(int[] nums){
        int length = nums.length;
        // length/2 反转
        for (int i=0;i<length/2;i++){
            // 数组位置交换
            int tmp = nums[i];
            nums[i] = nums[length-i-1];
            nums[length-i-1] =tmp;
        }
    }

    /**
     * 方法三
     * 一个一个替换
     */
    @Test
    public void rotate3() {
        int[] nums = {1,2,3,4,5,6,7};
        // 数组长度
        int length = nums.length;
        // k 为移动的位置
        int k = 2;

        k = k % length;
        //每次往后移一位，移动k次
        for (int i = 0; i < k; i++) {
            // 最后一个元素
            int fir = nums[length - 1];
            //从倒数第二个元素开始移动 todo 为什么 length - 2 ==》主要是最后一个元素不需要移动，在计算过程直接赋值为第一个元素，此处从倒数第二个元素逆序处理。
            for (int j = length - 2; j >= 0; j--) {
                // 交换元素下标位置，既是向后移动
                nums[j + 1] = nums[j];
            }
            //每次的第一个元素都是移动前的最后一个
            nums[0] = fir;
        }
    }

    /**
     * 方法四
     */
    @Test
    public void rotate4() {
        int[] nums = {1,2,3,4,5,6,7};
        // 数组长度
        int length = nums.length;
        // k 为移动的位置
        int k = 2;
        k = k % length;
        int[] nnums = new int[length];
        for (int i = 0; i < length; i++) {
            // 每个元素移动的位置
            int num = (i + k) % length;
            // 新数组新位置更新为原来的值
            nnums[num] = nums[i];

        }
        for (int i = 0; i < length; i++) {
            // 新数组的值赋予到原来的数组
            nums[i] = nnums[i];
        }
    }

}
