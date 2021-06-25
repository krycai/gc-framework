package com.allen.algorithm.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuguocai on 2021/6/25 9:39
 *
 * KMP算法
 *   https://zhuanlan.zhihu.com/p/76348091
 *
 *   https://www.geekxh.com/1.3.%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%B3%BB%E5%88%97/306.html#_02%E3%80%81%E6%80%BB%E7%BB%93
 */
public class KmpStr {

    /**
     * BF算法
     */
    @Test
    public void testBf(){
        String haystack = "hello", needle = "lo";
        char[] haystackArr = haystack.toCharArray();
        char[] needleArr = needle.toCharArray();

        int l1 = haystackArr.length;
        int l2 = needleArr.length;

        int i =0, j = 0;
        while (i < l1 && j < l2) {
            // 判断目标串与模式串的元素是否相等，相等则下标+1比较下一个元素
            if (haystackArr[i] == needleArr[j]){
                i++;
                j++;
            }else {// 不相等
                // i -= j - 1  既是 i = i -j +1
                i -= j - 1;
                j = 0;
            }
        }

        int tmp = -1;
        // j == l2 模式串匹配
        if (j == l2) {
            // 获取元素下标位置
            tmp = i - j;
        }
        System.out.println(tmp);

    }

    /**
     *  KMP算法  KMP算法的想法是，设法利用这个已知信息，不要把"搜索位置"移回已经比较过的位置，继续把它向后移，这样就提高了效率。
     *
     *  "前缀"和"后缀"。 "前缀"指除了最后一个字符以外，一个字符串的全部头部组合；"后缀"指除了第一个字符以外，一个字符串的全部尾部组合。
     *
     *  "部分匹配值"就是"前缀"和"后缀"的最长的共有元素的长度。以"ABCDABD"为例，
     *  　－　"A"的前缀和后缀都为空集，共有元素的长度为0；
     *
     * 　　－　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
     *
     * 　　－　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
     *
     * 　　－　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
     *
     * 　　－　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
     *
     * 　　－　"ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；
     *
     * 　　－　"ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
     *
     * next 数组： [0,0,0,0,1,2,0]
     */
    @Test
    public void test(){
        String haystack = "BBC ABCDAB ABCDABCDABDE", needle = "ABCDABD";
        char[] haystackArr = haystack.toCharArray();
        char[] needleArr = needle.toCharArray();

        int l1 = haystackArr.length;
        int l2 = needleArr.length;
        // next 数组
        int[] next = next(needle);
        int i =0, j = 0;

        while (i < l1 && j < l2) {
            if (j == -1 || haystackArr[i] == needleArr[j]){
                i++;
                j++;
            }else {
                j = next[j];
            }
        }

        int flag = -1;

        if (j == l2) {
            flag = i - j;
        }
        System.out.println(flag);
    }

    /**
     * 求 next 数组
     * @param s
     * @return
     */
    private int[] next(String s){
        //获取模式串的长度
        int len = s.length();
        //把模式串转换成char数组
        char[] p = s.toCharArray();
        //声明next数组,最后返回
        int[] next = new int[len];
        //第一位肯定是-1
        next[0] = -1;
        //第二位肯定是0
        next[1] = 0;
        //遍历next数组用
        int j = 1;
        //next[j]=k  k就是next数组中的值
        int k = 0;

        while (j < len - 1){
            //回溯到0都不匹配,则next值为0
            if (k == -1){
                j++;
                k = 0;
                next[j] = 0;
            } else if (p[j] == p[k]){     //找到了匹配的,对应next值+1
                j++;
                k++;
                next[j] = k;
            } else {                    //不匹配,指针回溯
                k = next[k];
            }
        }
        //返回next数组
        System.out.println("next数组："+ Arrays.toString(next));
        return next;
    }

}
