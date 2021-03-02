package com.allen.algorithm.arry;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by xuguocai on 2021/3/1 11:32  两个数组的交集
 */
public class ArryListTest {

    /**
     * 两个数组的交集
     */
    @Test
    public void getSameNumber(){
        int[] one = {1,2,3,4,5};
        int[] two = {3,4,5,6,7};
        List<Integer> set = new ArrayList<>();
        for (int i=0;i< one.length;i++){
            for (int j=0;j<two.length;j++){
                if (one[i] == two[j]){
                    System.out.print(one[i]  );
                    set.add(one[i]);
                }
            }
        }

    }

    /**
     * 两个数组的交集
     */
    @Test
    public void getSameNumber2(){
        int[] one = {1,3,3,4,5};
        int[] two = {3,4,5,6,3};

        Map<Integer,Integer> map = new HashMap<>();

        for (int i=0;i< one.length;i++){
            int k = 0;
            // 判断数组中存在相同的元素
            Integer value = map.get(one[i]);
            if (value !=null && value > 0){
                k = map.get(one[i]);
            }
            // 元素在数组出现的次数
            k +=1;
            map.put(one[i],k);
        }

        List<Integer> list = new ArrayList();
        for (int j=0;j<two.length;j++){
            // 若数组中存在，则取元素并减少元素的出现的次数
            Integer value = map.get(two[j]);
            if (value !=null && value > 0){
                int h = value;
                h -= 1;
                map.put(two[j],h);
                list.add(two[j]);
            }
        }

        // 查看交集
        list.forEach(item ->{
            System.out.print(item+" ");
        });
    }

    /**
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？我们分析一下，假如两个数组都是有序的，分别为：arr1 = [1,2,3,4,4,13]，arr2 = [1,2,3,9,10]
     */
    @Test
    public void getSameNum(){
        int[] arr1 = {1,2,3,4,4,13},arr2 = {1,2,3,9,10};
        List<Integer> list = new ArrayList<>();
        int i =0,j=0;
        // 结束循环的标识
        boolean flag = true;
        while (flag){
            // 数组的长度遍历完之后，结束遍历
            if (i == arr1.length-1 && j == arr2.length -1){
                flag = false;
            }
            System.out.println("i=="+i+" j==="+j);
            int k =arr1[i],v=arr2[j];
            // 元素相等，同时移动两个数组的下标
            if (k == v){
                list.add(k);
                i +=1;
                j +=1;
            }else if (k < v){ // 元素小于，则移动小元素的一个数组下标，既是 +1
                i +=1;
            }else { // 否则另一个元素数组的下标
                j +=1;
            }

        }

        list.forEach(item ->{
            System.out.print(item+" ");
        });
    }

    /**
     * 获取最长的公共前缀:编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，则返回""
     */
    @Test
    public void getSameFirstStr(){
        String[] str = {"string","sttreet","stringflying","stringflying"};
        // 取 参考元素
        String first = str[0];
        // 输出元素
        String out = null;
        // 相同元素的的长度
        int index = first.length();
        for (int i=0;i<str.length;i++){
            String tmp = str[i];
            // 同一个元素，则跳出当前循环
            if (first.equals(tmp)){
                continue;
            }
            // 捕获相同的元素的前缀
            while (first.indexOf(tmp) != 0){
                int length = tmp.length();
                tmp = tmp.substring(0,length-1);
            }

            // 获取元素的位置
            if (StringUtils.isNotEmpty(tmp)){
                System.out.println("tmp:"+tmp);
                int pp = 0;
                int tmpLen = tmp.length();
                if (index == tmpLen){
                    pp = tmpLen;
                }else if (tmpLen > index){
                    System.out.println("取index");
                    pp = index;
                }else {
                    pp = tmpLen;
                }
                if (pp < index){ // 比较他们的长度，取最小的
                    index = pp;
                }
                out = first.substring(0,index);
                System.out.println("最大公共值："+out);
            }else {
                System.out.println("无最长公共前缀");
                out = "";
                break;
            }

        }

        System.out.println("最大公共前缀："+out);
    }

    /**
     * 获取最长的公共前缀.
     */
    @Test
    public void longestCommonPrefix() {
        String[] strs = {"string","sttreet","stringflying","stringflying"};
        int count = strs.length;
        String prefix = "";
        if(count != 0){
            // 截取的基准参数，与其他元素对比
            prefix = strs[0];
        }
        for(int i=0; i<count; i++){
            //关键代码，不断的从后往前截取字符串，然后与之相比，直到startsWith()返回true
            while(!strs[i].startsWith(prefix)){// 不是前缀，则截取元素；若是元素，则跳出循环
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        System.out.println("===="+prefix);
//        return prefix;
    }
}
