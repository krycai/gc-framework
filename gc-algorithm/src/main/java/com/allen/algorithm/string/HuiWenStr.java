package com.allen.algorithm.string;

import org.junit.Test;

/**
 * @author xuguocai on 2021/6/24 14:08 回文串
 *
 *
 *
 */
public class HuiWenStr {

    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 1、去掉其余的字符，组成新的数组
     * 2、数组元素前后对称比较，存在相同则是回文串，不相同则不是。
     *
     */
    @Test
    public void test(){
        String str = "A man, a plan, a canal: Panama";

        String all = str.toLowerCase().replaceAll("[^0-9a-z]", "");
        System.out.println(all);

        char[] chars = all.toCharArray();
        int index = 0;
        int last = chars.length-1;
        boolean flag = false;
        for (int i=0;i<chars.length/2+1;i++){
            // 第一次：前后比较
            if (index != last){
                char first = chars[index];
                char finalVal = chars[last];
                if (first == finalVal){
                    index++;
                    last--;
                    flag = true;
                }else {
                    flag = false;
                    break;
                }
            }else {
                flag = true;
                break;
            }
        }

        System.out.println(flag);
    }

    @Test
    public void isPalindrome() {
        String s = "A man, a plan, a canal: Panama";
        s = s.toLowerCase().replaceAll("[^0-9a-z]", "");
        char[] c = s.toCharArray();
        int i = 0, j = c.length - 1;
        boolean flag = true;
        while (i < j) {
            if (c[i] != c[j]){
                flag = false;
            }
            i++;
            j--;
        }
        System.out.println(flag);
    }

    /**
     * 非重点元素，不参与比较
     */
    @Test
    public void isPalindrome2() {
        String s = "A man, a plan, a canal: Panama";
        s = s.toLowerCase();
        char[] c = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        boolean flag = true;
        while(i < j) {
            // 不是 0-9a-z,则跳过
            if (!((c[i] >= '0' && c[i] <= '9') || (c[i] >= 'a' && c[i] <= 'z'))) {
                i++;
                continue;
            }
            // 不是 0-9a-z,则跳过
            if (!((c[j] >= '0' && c[j] <= '9') || (c[j] >= 'a' && c[j] <= 'z'))) {
                j--;
                continue;
            }
            if(c[i] != c[j]){
                flag = false;
            }
            i++;
            j--;
        }
        System.out.println(flag);
    }

}
