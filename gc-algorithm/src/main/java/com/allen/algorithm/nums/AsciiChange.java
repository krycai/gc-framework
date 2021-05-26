package com.allen.algorithm.nums;

import java.util.Scanner;

/**
 * @Author Allen 2021/5/26 21:39  写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 **/
public class AsciiChange {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 循环连续读取
        while(sc.hasNext()){
            String line = sc.nextLine();
//            printResult16(line);
            printResult8(line);
        }
    }

    /**
     * 接受一个十六进制的数，输出该数值的十进制表示。
     * @param line
     */
    private static void printResult16(String line) {
        // 截取前面的 0x，转为十进制,不能有前缀 0x
        Integer integer = Integer.parseInt(line.substring(2), 16);
        System.out.println(integer);
        // 十进制再转为16进制
        String toHexString = Integer.toHexString(integer);
        System.out.println("0x"+toHexString);
    }

    /**
     * 接受一个八进制的数，输出该数值的十进制表示。 todo
     * @param line
     */
    private static void printResult8(String line) {
        // 8进制转10进制
        Integer integer = Integer.parseInt(line, 8);
        System.out.println(integer);
        // 十进制再转为8进制
        String toHexString = Integer.toOctalString(integer);
        System.out.println(toHexString);
    }

}
