package com.allen.algorithm.nums;

import java.util.Scanner;

/**
 * @author xuguocai on 2021/5/27 10:09  输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ） 最后一个数后面也要有空格
 */
public class FactorNum {

    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);

        long num = scanner.nextLong();
        // Math.sqrt 取平方根
        long k = (long) Math.sqrt(num);
        System.out.println(k);

        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        // num 也是质数，最后取
        System.out.println(num == 1 ? "": num+" ");
    }

}
