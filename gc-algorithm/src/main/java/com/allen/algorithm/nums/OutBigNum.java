package com.allen.algorithm.nums;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xuguocai on 2021/6/21 15:38
 */
public class OutBigNum {

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     */
    @Test
    public void test(){
        String next = "4";
        // 数字
        Integer integer = Integer.valueOf(next);
        // Math.pow 次方运算
        int max = (int)Math.pow(10,integer);
        System.out.println("最大数："+max);
        for (int i =1;i <max;i++){
            System.out.print(i+" ");
        }
    }

    /**
     * 不允许使用math.pow，请手动实现一下
     */
    @Test
    public void test2(){
        int num = 3;
        int tmp = 1;
        for (int i=1;i<=num;i++){
            tmp *=10;
        }

        System.out.println("最大数："+tmp);

        for (int i =1;i <tmp;i++){
            System.out.print(i+" ");
        }
    }

    /**
     * https://www.geekxh.com/1.3.%E5%AD%97%E7%AC%A6%E4%B8%B2%E7%B3%BB%E5%88%97/304.html#_04%E3%80%81%E9%A2%98%E7%9B%AE%E7%BB%A7%E7%BB%AD%E5%8D%87%E7%BA%A7
     *
     * 如果阈值超出long类型，该怎么办呢？
     *
     * 我们该如何模拟一个 “最大的n位十进制数” 呢？其实也是一样的，我们采用 char 数组进行存储。而我们每次递增1，相当于进行一次字符串相加的运算。
     */
    @Test
    public void test3(){
        int num = 2;
        printNumbers(num);
    }
    public void printNumbers(int n) {
        //声明字符数组,用来存放一个大数
        char[] number = new char[n];
        Arrays.fill(number, '0');
        while (!incrementNumber(number)) {
            saveNumber(number); //存储数值
        }
    }

    private boolean incrementNumber(char[] number) {
        //循环体退出标识
        boolean isBreak = false;
        //进位标识
        int carryFlag = 0;
        int l = number.length;
        for (int i = l - 1; i >= 0; i--) {
            //取第i位的数字转化位int
            int nSum = number[i] - '0' + carryFlag;
            if (i == l - 1) {
                //最低位加1
                ++nSum;
            }
            if (nSum >= 10) {
                if (i == 0) {
                    isBreak = true;
                } else {
                    //进位之后减10，并把进位标识设置为1
                    nSum -= 10;
                    carryFlag = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) (nSum + '0');
                break;
            }
        }
        return isBreak;
    }
    private void saveNumber(char[] number) {
        boolean isBegin0 = true;
        for (char c : number) {
            if (isBegin0 && c != '0') {
                isBegin0 = false;
            }
            if (!isBegin0) {
                // 到这里并没有继续往下实现一个存储数组的版本，是因为原题其实就是要求打印数值。
                // 这道题目在leetcode上被改动成返回int数组的形式，也只是为了测试方便，
                // 本身leetcode并没有提供对应的大数测试样例，也是担心其内存溢出。
                // 总之大家知道本题的考察点所在就可以了。
                System.out.print(c+" ");
            }
        }
        System.out.println();
    }
}
