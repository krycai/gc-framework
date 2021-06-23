package com.allen.algorithm.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/6/23 10:19  编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出
 */
public class ReverseStr {

    /**
     * 数组逆序处理
     */
    @Test
    public void test(){
        char[] arr = {'a','b','c','d','e','f'};
        int length = arr.length;
        char[] tmp = new char[length];
        int j=0;
        for (int i=length-1;i>=0;i--){
            char aa = arr[i];
            tmp[j]=aa;
            j++;
        }

        System.out.println(Arrays.toString(tmp));
    }

    /**
     * 数组开始位置与最后位置交换，中间位置结束交换
     */
    @Test
    public void test2(){
        char[] arr = {'a','b','c','d','e','f','g'};
        int left = 0;
        int right = arr.length-1;

        for (int i=0;i<arr.length;i++){
            if (left < right){
                // 交互位置
                char tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;

                left ++;
                right --;
            }else  {
                System.out.println("---------位置交换完毕--------");
                break;
            }
        }

        System.out.println(Arrays.toString(arr));
    }

}
