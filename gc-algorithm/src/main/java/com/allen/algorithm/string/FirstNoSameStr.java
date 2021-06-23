package com.allen.algorithm.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuguocai on 2021/6/23 13:49 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1 。
 */
public class FirstNoSameStr {

    /**
     * 利用 map 的特性（元素不重复），遍历重复加1（value值），后面拿map 取值判断
     */
    @Test
    public void test(){
        String s = "loveleetcode";
        Map<Character,Integer> charMap=new HashMap<>(26);
        char[] chars=s.toCharArray();

        for (char c: chars) {
            Integer count=charMap.get(c);
            // 判断出现的次数
            count=count==null?1:count+1;
            charMap.put(c,count);
        }

        int j = -1;

        for (int i = 0; i <s.length() ; i++) {
            // 刚开始等于1的元素是第一个不重复的元素
            if (charMap.get(chars[i])==1){
                j = i;
                break;
            }
        }

        System.out.println("第一个不相同的元素:"+j);
    }

}
