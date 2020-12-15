package com.allen.thread.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @program: MultiThread
 * @description: AtomicArrayTest
 * @author: allen小哥
 * @Date: 2019-12-15 14:32
 **/
@Slf4j
public class AtomicArrayTest {

    private static int[] values = new int[]{1,2,3};
    private static long[] longs = new long[]{1,2,3};
    private static String[] strings = new String[]{"a","b"};
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(values);
    private static AtomicLongArray atomicLongArray = new AtomicLongArray(longs);
    private static AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(strings);

    public static void main(String[] args) {
        int andAdd = atomicIntegerArray.getAndAdd(2, 5); // 返回原来的值
        log.info("andAdd:{}",andAdd);
        log.info("获取数据:{}",atomicIntegerArray.get(2));
    }

}
