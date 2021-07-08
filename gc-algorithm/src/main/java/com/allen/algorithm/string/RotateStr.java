package com.allen.algorithm.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/7/8 13:59  旋转字符串
 *
 * 给定两个字符串, A 和 B。A 的旋转操作就是将 A 最左边的字符移动到最右边。例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
 */
public class RotateStr {

    /**
     *  利用 while 处理
     */
    @Test
    public void test(){
        String a = "abcde",b = "bcdea";

        int len = a.length();
        int index = 0;
        boolean flag = false;
        while(true){
            if (a.equals(b)){
                System.out.println("--旋转成功--");
                flag = true;
                break;
            }
            String tmpA = a.substring(0,1);
            String tmpB = a.substring(1,len);
            a= tmpB + tmpA;
            System.out.println(a);

            if (index == len){
                System.out.println("=======不是旋转=======");
                break;
            }
            ++index;
        }

        System.out.println("是否是旋转字符串："+flag);
    }

    @Test
    public void rotateString() {
        String A = "abcde",B = "bcdea";
        if (A.equals("") && B.equals("")) {
            System.out.println("----------不是旋转字符串");
            return;
        }
        boolean flag = false;
        int len = A.length();
        for (int i = 0; i < len; i++) {
            String first = A.substring(0, 1);
            String last = A.substring(1, len);
            A = last + first;
            if (A.equals(B)) {
                System.out.println("----------是旋转字符串");
                flag = true;
                break;
            }
        }
        System.out.println("----------是不是旋转字符串:"+flag);
    }

    /**
     *  判断 B 是否是 A+A 的字串,若是，则为旋转字符串
     */
    @Test
    public void rotateString2() {
        String A = "abcde",B = "bcdea";

        String tmp = A + A;
        System.out.println(tmp.contains(B));

    }

    /**
     * 利用KMP算法
     */
    @Test
    public void rotateStringKmp() {
        String A = "abcde",B = "bcdea";
        int N = A.length();
        if (N != B.length()){
            System.out.println("----------不是旋转字符串");
            return;
        }
        if (N == 0) {
            System.out.println("----------不是旋转字符串");
            return;
        }

        //Compute shift table
        int[] shifts = new int[N+1];
        // 字符数组存放默认 值
        Arrays.fill(shifts, 1);
        int left = -1;
        boolean flag = false;
        for (int right = 0; right < N; ++right) {
            while (left >= 0 && (B.charAt(left) != B.charAt(right))){
                left -= shifts[left];
            }
            shifts[right + 1] = right - left++;
        }

        //Find match of B in A+A
        int matchLen = 0;
        for (char c: (A+A).toCharArray()) {
            while (matchLen >= 0 && B.charAt(matchLen) != c){
                matchLen -= shifts[matchLen];
            }
            if (++matchLen == N) {
                flag= true;
                break;
            }
        }

        System.out.println("=-=-="+flag);
    }
}
