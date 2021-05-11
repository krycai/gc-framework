package com.allen.jvm;

/**
 * @Author Allen 2021/5/1 8:12    -XX:+PrintGCDetails
 **/
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2,allocation3,allocation4,allocation5;
        allocation1 = new byte[32000*1024];
        allocation2 = new byte[1000*1024];
        allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
    }

}
