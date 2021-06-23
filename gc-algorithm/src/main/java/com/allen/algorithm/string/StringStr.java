package com.allen.algorithm.string;

import org.junit.Test;

/**
 * @author xuguocai on 2021/6/23 15:47 实现 Sunday 匹配
 *
 * 串：串是字符串的简称
 * 空串：长度为零的串称为空串
 * 主串：包含子串的串相应地称为主串
 * 子串：串中任意个连续字符组成的子序列称为该串的子串
 * 模式串：子串的定位运算又称为串的模式匹配，是一种求子串第一个字符在主串中序号的运算。被匹配的主串称为目标串，子串称为模式串。
 *
 */
public class StringStr {

    /**
     * 实现 strStr() 函数。给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。
     */
    @Test
    public void test(){
        String haystack = "hello", needle = "fo";
        int bigLen = haystack.length();
        int minLen = needle.length();

        int tmpLen = bigLen - minLen;
        System.out.println("字符串长度："+bigLen+" 截取长度:"+minLen+" 遍历次数："+tmpLen);
        int j = -1;
        for (int i=0;i<=tmpLen;i++){
            String tmp = haystack.substring(i,minLen+i);
            if (needle.equals(tmp)){
                j=i;
                System.out.println("相同的值："+tmp);
                break;
            }
        }

        System.out.println("截取后相同的位置索引:"+j);
    }

    /**
     * Sunday算法,其核心思想是：在匹配过程中，模式串发现不匹配时，算法能跳过尽可能多的字符以进行下一步的匹配，从而提高了匹配效率。
     *
     * 1)、对于SUNDAY，就是找到模式串后的第一个字符。因为，无论模式串移动多少步，模式串后的第一个字符都要参与下一次比较
     * 2）、查看模式串中是否包含这个元素，如果不包含那就可以跳过一大片，从该字符的下一个字符开始比较。
     *
     * 1、对齐目标串和模式串，从前向后匹配
     * 2、关注主串中位于模式串后面的第一个元素（核心）
     * 3、如果关注的字符没有在子串中出现则直接跳过
     * 4、否则开始移动模式串，移动位数 = 子串长度 - 该字符最右出现的位置(以0开始)
     *
     * https://www.geekxh.com/1.3.%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%B3%BB%E5%88%97/303.html#_02%E3%80%81sunday-%E5%8C%B9%E9%85%8D
     *
     *
     *
     */
    @Test
    public void test2(){
        String haystack = "hello", needle = "lo";
        int i = strStr(haystack, needle);
        System.out.println(i);
    }

    public int strStr(String origin, String aim) {
        if (origin == null || aim == null) {
            return 0;
        }
        if (origin.length() < aim.length()) {
            return -1;
        }
        //目标串匹配索
        int originIndex = 0;
        //模式串匹配索引
        int aimIndex = 0;
        // 成功匹配完终止条件：所有aim均成功匹配
        while (aimIndex < aim.length()) {
            // 针对origin匹配完，但aim未匹配完情况处理 如 mississippi sippia
            if (originIndex > origin.length() - 1) {
                return -1;
            }
            if (origin.charAt(originIndex) == aim.charAt(aimIndex)) {
                // 匹配则index均加1
                originIndex++;
                aimIndex++;
            } else {
                //在我们上面的样例中，第一次计算值为6，第二次值为13
                int nextCharIndex = originIndex - aimIndex + aim.length();
                //判断下一个目标字符（上面图里的那个绿框框）是否存在。
                if (nextCharIndex < origin.length()) {
                    // 判断目标字符在模式串中匹配到，返回最后一个匹配的index todo 关键点（存在相同的元素，以此点拓展处理）
                    int step = aim.lastIndexOf(origin.charAt(nextCharIndex));
                    if (step == -1) {
                        // 不存在的话，设置到目标字符的下一个元素
                        originIndex = nextCharIndex + 1;
                    } else {
                        // 存在的话，移动对应的数字（参考上文中的存在公式）
                        originIndex = nextCharIndex - step;
                    }
                    //模式串总是从第一个开始匹配
                    aimIndex = 0;
                } else {
                    return -1;
                }
            }
        }
        return originIndex - aimIndex;
    }
}
