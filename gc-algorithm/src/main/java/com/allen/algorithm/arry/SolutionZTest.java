package com.allen.algorithm.arry;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by xuguocai on 2021/3/4 14:39  Z 字形变换 todo 没有头绪
 */
public class SolutionZTest {

    /**
     * copy  https://www.geekxh.com/1.0.%E6%95%B0%E7%BB%84%E7%B3%BB%E5%88%97/009.html#_02%E3%80%81%E9%A2%98%E7%9B%AE%E5%88%86%E6%9E%90
     */
    @Test
    public void test(){
        int numRows = 3;
        String s = "abcdefghijklmnwqprozxv";
        if (numRows == 1){
            System.out.println("结束操作:"+s);
            return ;
        }
        //创建数组
        String[] arr = new String[numRows];
        Arrays.fill(arr, "");
        // 字符串变化 字符数组
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));

        int len = chars.length;
        // 每 2n-2 即为一个周期
        int period = numRows * 2 - 2;
        System.out.println("period:"+period+" len:"+len);
        for (int i = 0; i < len; i++) {
            int mod = i % period;
            //
            if (mod < numRows) {
                arr[mod] += chars[i];
            } else {
                System.out.println("mod:"+mod);
                arr[period - mod] += String.valueOf(chars[i]);
                System.out.println( arr[period - mod]);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String ch : arr) {
            res.append(ch);
        }
        System.out.println(res);
    }

}
