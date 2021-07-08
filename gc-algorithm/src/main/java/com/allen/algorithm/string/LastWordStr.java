package com.allen.algorithm.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/7/8 15:13  最后一个单词的长度
 *
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 */
public class LastWordStr {

    /**
     * 关键点： 仅包含大小写字母和空格 ' ' 的字符串 s
     *   因素： 单词 是 大小写字母 组成
     *   切换点： 空格
     */
    @Test
    public void test(){
        String tmp = "Hello World aaaaab ";

        String[] s = tmp.split(" ");
        int length = s.length;

        System.out.println(s[length-1].length());
    }

    /**
     * 利用 字符 遍历的次数，作为最后一个元素的长度
     *
     * 1、利用 count　来统计
     * ２、利用　len 来统计
     *
     */
    @Test
    public void lengthOfLastWord() {
        String s = "Hello World aaaaab ";
        if(s == null || s.length() == 0){
            System.out.println("-------------字符串为空---------------");
            return;
        }
        // 记录空格位置
        int len = 0;
        int count = 0;
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) == ' '){
                if(count == 0) {
                    // 刚开始为空格，则跳过
                    continue;
                }
                len = i;
                break;
            }
            // 逆序计算空格位置
            count++;
        }

        System.out.println("字符统计最后一个单词长度----"+count);
        System.out.println("-------------利用字符串长度，然后截取---------------");
        String substring = s.substring(len).trim();
        System.out.println("最后一个字符串==="+substring+" ，最后一个字符串长度==="+substring.length());
    }

    /**
     * 利用 api 操作
     */
    @Test
    public void lengthOfLastWord2() {
        String s = "Hello World aaaaab ";
        s = s.trim();
        int start = s.lastIndexOf(" ") + 1;

        System.out.println(s.substring(start).length());
    }
}
